package akka;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.CompletionStage;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final ActorSystem system = ActorSystem.create("QuickStart");
    final Materializer materializer = ActorMaterializer.create(system);

    final Source<Integer, NotUsed> source = Source.range(1, 5);

    final Span span = GlobalTracer.get().buildSpan("parent")
        .withTag(Tags.COMPONENT, "streams-parent")
        .start();

    try (Scope scope = GlobalTracer.get().activateSpan(span)) {
      final CompletionStage<Done> done = source.runForeach(i -> {
        Util.checkActiveSpan();
        System.out.println(i);
      }, materializer);

      done.thenRun(() -> {
        Util.checkActiveSpan();
        system.terminate();
      });
    } finally {
      span.finish();
    }

    Util.checkSpan("streams-parent", 1);
  }


}
