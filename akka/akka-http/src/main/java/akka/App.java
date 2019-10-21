package akka;

import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import util.Util;

public class App extends AllDirectives {
  public static void main(String[] args) throws Exception {
    client();
  }

  private static void client() throws Exception {
    final ActorSystem system = ActorSystem.create();
    final Materializer materializer = ActorMaterializer.create(system);

    final CompletionStage<HttpResponse> stage = Http.get(system)
        .singleRequest(HttpRequest.GET("http://www.google.com"));

    final HttpResponse httpResponse = stage.whenComplete(new BiConsumer<HttpResponse, Throwable>() {
      @Override
      public void accept(HttpResponse httpResponse, Throwable throwable) {
        System.out.println(httpResponse.status());
      }
    }).toCompletableFuture().get();

    httpResponse.entity().getDataBytes().runForeach(param -> {

    }, materializer);

    stage.thenRun(() -> {
      system.terminate();
    });
  }

  public static void server() throws Exception {
    // boot up server using the route as defined below
    ActorSystem system = ActorSystem.create("routes");

    final Http http = Http.get(system);
    final ActorMaterializer materializer = ActorMaterializer.create(system);

    //In order to access all directives we need an instance where the routes are define.
    App app = new App();

    final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute()
        .flow(system, materializer);
    final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
        ConnectHttp.toHost("localhost", 8080), materializer);

    System.out.println("Server online at http://localhost:8080/");

    TimeUnit.SECONDS.sleep(10);

    URL obj = new URL("http://localhost:8080/hello");
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("Response Code : " + responseCode);

    binding
        .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
        .thenAccept(unbound -> system.terminate()); // and shutdown when done

    Util.checkSpan("akka", 1);
  }

  private Route createRoute() {
    return concat(
        path("hello", () ->
            get(() ->
                complete("<h1>Say hello to akka-http</h1>"))));
  }
}
