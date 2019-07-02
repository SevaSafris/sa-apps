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
    for (MockSpan span : tracer.finishedSpans()) {
      if (span.tags().get(Tags.COMPONENT.getKey()).equals(component)) {
        found = true;
        System.out.println("Found " + component + " span");
        break;
      }
    }
    if (!found) {
      throw new RuntimeException(component + " span not found");
    }

    if (tracer.finishedSpans().size() != spanCount) {
      throw new RuntimeException(tracer.finishedSpans().size() +
          " spans instead of " + spanCount);
    }
  }

}
