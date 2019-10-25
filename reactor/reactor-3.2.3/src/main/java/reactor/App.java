package reactor;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.atomic.AtomicReference;
import reactor.core.publisher.Mono;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final Span initSpan = GlobalTracer.get().buildSpan("foo")
        .withTag(Tags.COMPONENT, "parent-reactor").start();
    final AtomicReference<String> spanInSubscriberContext = new AtomicReference<>();
    try (final Scope scope = GlobalTracer.get().scopeManager().activate(initSpan)) {
      Mono.subscriberContext().map(context -> (context.get(Span.class)).context().toSpanId())
          .doOnNext(spanInSubscriberContext::set).block();
    } finally {
      initSpan.finish();
    }

    System.out.println("Span id: " + initSpan.context().toSpanId());
    if (!spanInSubscriberContext.get().equals(initSpan.context().toSpanId())) {
      System.err.println("ERROR: not equal span id");
    }

    Util.checkSpan("parent-reactor", 1);

  }
}
