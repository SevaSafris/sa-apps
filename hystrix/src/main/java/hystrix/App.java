package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final Span parent = GlobalTracer.get().buildSpan("parent")
        .withTag(Tags.COMPONENT, "hystrix")
        .start();
    try (Scope ignored = GlobalTracer.get().activateSpan(parent)) {
      final String res = new HystrixTestCommand().execute();
      if (!res.equalsIgnoreCase("test")) {
        System.err.println("ERROR: failed hystrix res: " + res);
        System.exit(-1);
      }
    } finally {
      parent.finish();
    }

    Util.checkSpan("hystrix", 1);
  }

  private static class HystrixTestCommand extends HystrixCommand<String> {

    HystrixTestCommand() {
      super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() {
      System.out.println("Active span: " + GlobalTracer.get().activeSpan());
      if (GlobalTracer.get().activeSpan() == null) {
        System.err.println("ERROR: no active span");
        System.exit(-1);
      }

      return "test";
    }
  }
}
