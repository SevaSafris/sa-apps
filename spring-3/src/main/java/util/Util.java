package util;

import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.lang.reflect.Field;

public class Util {

  public static void checkSpan(String component, int spanCount) throws Exception {
    final Field field = GlobalTracer.get().getClass().getDeclaredField("tracer");
    field.setAccessible(true);
    final Object ob = field.get(GlobalTracer.get());
    MockTracer tracer;
    if (ob instanceof MockTracer) {
      tracer = (MockTracer) ob;
    } else {
      return;
    }
    boolean found = false;
    System.out.println("Spans: " + tracer.finishedSpans());
    for (MockSpan span : tracer.finishedSpans()) {
      System.out.println("Span Component: " + span.tags().get(Tags.COMPONENT.getKey()));
      if (span.tags().get(Tags.COMPONENT.getKey()).equals(component)) {
        found = true;
        System.out.println("Found " + component + " span");
        break;
      }
    }
    if (!found) {
      // System.err.println(component + " span not found");
      // System.exit(-1);
    }

    if (tracer.finishedSpans().size() != spanCount) {
     System.err.println(tracer.finishedSpans().size() +
          " spans instead of " + spanCount);
     System.exit(-1);
    }

    System.out.println("Spans: " + tracer.finishedSpans());
  }

}
