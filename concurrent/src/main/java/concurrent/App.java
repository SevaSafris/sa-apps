package concurrent;


import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {
  public static void main(String[] args) throws InterruptedException {

    final Span parent = GlobalTracer.get().buildSpan("parent").start();

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
    } finally {
      parent.finish();
    }

    TimeUnit.SECONDS.sleep(10);

  }


}
