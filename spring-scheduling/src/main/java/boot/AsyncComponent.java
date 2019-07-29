package boot;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncComponent {

  @Async
  public Future<String> async() {
    final Span span = GlobalTracer.get().activeSpan();
    System.out.println("Active span: " + span);
    if (span == null) {
      System.err.println("No active span");
      System.exit(-1);
    }
    return new AsyncResult<>("whatever");
  }

}
