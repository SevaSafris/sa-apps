package asynchttpclient;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    AsyncHttpClient client = new DefaultAsyncHttpClient();

    Request request = new RequestBuilder(HttpConstants.Methods.GET)
        .setUrl("http://www.google.com")
        .build();

    final Response response = client.executeRequest(request,
        new AsyncCompletionHandler<Response>() {
          @Override
          public Response onCompleted(Response response) {
            Span span = GlobalTracer.get().activeSpan();
            System.out.println("Active span: " + span);
            if (span == null) {
              System.err.println("Missing active span");
              System.exit(-1);
            }
            return response;
          }
        }
    ).get(10, TimeUnit.SECONDS);
    System.out.println(response.getStatusText());

    Util.checkSpan("java-asynchttpclient", 1);

    System.exit(0);
  }
}
