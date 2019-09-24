package akka;

import static akka.pattern.Patterns.ask;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    ActorSystem system = ActorSystem.create("actorSystem");

    ActorRef actorRef = system.actorOf(TestActor.props(), "one");
    Timeout timeout = new Timeout(getDefaultDuration());

    Future<Object> future = ask(actorRef, "foo", timeout);
    Await.result(future, getDefaultDuration());

    Await.result(system.terminate(), getDefaultDuration());

    Util.checkSpan("java-akka", 2);

  }

  private static FiniteDuration getDefaultDuration() {
    return Duration.create(10, "seconds");
  }

  static class TestActor extends AbstractActor {

    static Props props() {
      return Props.create(TestActor.class, TestActor::new);
    }

    @Override
    public Receive createReceive() {
      return receiveBuilder()
          .matchAny(x -> {
            final Span span = GlobalTracer.get().activeSpan();
            System.out.println("Active span: " + span);
            if (span == null) {
              System.err.println("ERROR: no active span");
              System.exit(-1);
            }
            getSender().tell(true, getSelf());
          }).build();
    }
  }
}
