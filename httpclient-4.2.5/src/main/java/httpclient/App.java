package httpclient;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final HttpClient client = new DefaultHttpClient();
    final Span parent = GlobalTracer.get().buildSpan("parent").withTag(Tags.COMPONENT, "parent").start();
    try (Scope ignore = GlobalTracer.get().activateSpan(parent)) {
      final HttpResponse response = client.execute(new HttpGet("http://www.google.com"));
      System.out.println(response.getStatusLine().getReasonPhrase());
    }
    parent.finish();

    Util.checkSpan("java-httpclient", 2);
  }
}
