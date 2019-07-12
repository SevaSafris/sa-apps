package concurrent;


import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {

    final Span parent = GlobalTracer.get().buildSpan("parent")
        .withTag(Tags.COMPONENT, "parent").start();

    testExecutor(parent);
    testThread(parent);
    //testParallelStream(parent);

    parent.finish();

    Util.checkSpan("parent", 1);

    System.exit(0);

  }

  private static void testExecutor(Span parent) throws Exception {
    final ExecutorService service = Executors.newFixedThreadPool(10);
    try (Scope scope = GlobalTracer.get().activateSpan(parent)) {
      service.submit(new Runnable() {
        @Override
        public void run() {
          System.out.println("Active span: " + GlobalTracer.get().activeSpan());
          if (GlobalTracer.get().activeSpan() == null) {
            System.err.println("No active span");
            System.exit(-1);
          }
        }
      }).get();
    }
  }

  private static void testThread(Span parent) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Active span: " + GlobalTracer.get().activeSpan());
        if (GlobalTracer.get().activeSpan() == null) {
          System.err.println("No active span");
          System.exit(-1);
        }
      }
    });
    try (Scope scope = GlobalTracer.get().activateSpan(parent)) {
      thread.start();
    }
    thread.join();
  }

  private static void testParallelStream(Span parent) {
    try (Scope scope = GlobalTracer.get().activateSpan(parent)) {
      final int sum = IntStream.range(1, 10)
          .parallel()
          .map(operand -> {
            // TODO: here should be active span
            System.out.println("Thread: " + Thread.currentThread().getName() +
                " Active span: " + GlobalTracer.get().activeSpan());
            return operand * 2;
          }).sum();
      System.out.println("Sum: " + sum);
    }

  }

}
