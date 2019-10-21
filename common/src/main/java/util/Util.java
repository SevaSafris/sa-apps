package util;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.IsEqual.equalTo;

import io.opentracing.Span;
import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockSpan.LogEntry;
import io.opentracing.mock.MockTracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Util {

  public static void checkSpan(final String component, final int spanCount) throws Exception {
    final Field field = GlobalTracer.get().getClass().getDeclaredField("tracer");
    field.setAccessible(true);
    final Object ob = field.get(GlobalTracer.get());
    MockTracer tracer;
    if (ob instanceof MockTracer) {
      tracer = (MockTracer) ob;
    } else {
      return;
    }

    await().atMost(15, TimeUnit.SECONDS).until(reportedSpansSize(tracer), equalTo(spanCount));

    boolean found = false;
    System.out.println("Spans: " + tracer.finishedSpans());
    for (MockSpan span : tracer.finishedSpans()) {
      printSpan(span);
      if (component.equals(span.tags().get(Tags.COMPONENT.getKey()))) {
        found = true;
        System.out.println("Found " + component + " span");
      }
    }
    if (!found) {
      System.err.println("ERROR: " + component + " span not found");
      System.exit(-1);
    }

    if (tracer.finishedSpans().size() != spanCount) {
      System.err
          .println("ERROR: " + tracer.finishedSpans().size() + " spans instead of " + spanCount);
      System.exit(-1);
    }
  }

  private static void printSpan(MockSpan span) {
    System.out.println("Span: " + span);
    System.out.println("\tComponent: " + span.tags().get(Tags.COMPONENT.getKey()));
    System.out.println("\tTags: " + span.tags());
    System.out.println("\tLogs: ");
    for (LogEntry logEntry : span.logEntries()) {
      System.out.println("\t" + logEntry.fields());
    }
  }

  public static void checkActiveSpan() {
    final Span span = GlobalTracer.get().activeSpan();
    System.out.println("Active span: " + span);
    if (span == null) {
      System.err.println("Error: no active span");
      System.exit(-1);
    }
  }

  private static Callable<Integer> reportedSpansSize(final MockTracer tracer) {
    return new Callable<Integer>() {
      @Override
      public Integer call() {
        return tracer.finishedSpans().size();
      }
    };
  }

}
