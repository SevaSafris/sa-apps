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

    // Ask
    ActorRef askActorRef = system.actorOf(TestActor.props(), "ask");

    Timeout timeout = new Timeout(getDefaultDuration());

    Future<Object> future = ask(askActorRef, "ask", timeout);
    Await.result(future, getDefaultDuration());

    // Tell
    ActorRef tellActorRef = system.actorOf(TestActor.props(), "tell");
    tellActorRef.tell("tell", ActorRef.noSender());

    // Tell No Send
    ActorRef tellActorNoSendRef = system.actorOf(TestActorNoSend.props(), "tell-no-send");
    tellActorNoSendRef.tell("tell-no-send", tellActorNoSendRef);

    Util.checkSpan("java-akka", 6);

    Await.result(system.terminate(), getDefaultDuration());
  }

  private static FiniteDuration getDefaultDuration() {
    return Duration.create(15, "seconds");
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
            System.out.println("Received: " + x);
            System.out.println("Active span: " + span);
            if (span == null) {
              System.err.println("ERROR: no active span");
              System.exit(-1);
            }
            getSender().tell(true, getSelf());
          }).build();
    }
  }

  static class TestActorNoSend extends AbstractActor {

    static Props props() {
      return Props.create(TestActorNoSend.class, TestActorNoSend::new);
    }

    @Override
    public Receive createReceive() {
      return receiveBuilder()
          .matchAny(x -> {
            final Span span = GlobalTracer.get().activeSpan();
            System.out.println("Received: " + x);
            System.out.println("Active span: " + span);
            if (span == null) {
              System.err.println("ERROR: no active span");
              System.exit(-1);
            }
          }).build();
    }
  }
}
