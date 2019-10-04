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

    final Span parent = GlobalTracer.get().buildSpan("concurrent")
        .withTag(Tags.COMPONENT, "parent-concurrent").start();

    testExecutor(parent);
    testThread(parent);
    testParallelStream(parent);

    parent.finish();

    Util.checkSpan("parent-concurrent", 1);

    System.exit(0);

  }

  private static void testExecutor(Span parent) throws Exception {
    final ExecutorService service = Executors.newFixedThreadPool(10);
    try (Scope scope = GlobalTracer.get().activateSpan(parent)) {
      service.submit(new Runnable() {
        @Override
        public void run() {
          Util.checkActiveSpan();
        }
      }).get();
    }
  }

  private static void testThread(Span parent) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        Util.checkActiveSpan();
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
            Util.checkActiveSpan();
            return operand * 2;
          }).sum();
      System.out.println("Sum: " + sum);
    }

  }

}
