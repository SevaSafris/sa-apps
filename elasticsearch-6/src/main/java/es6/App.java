package es6;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class App {
  public static void main(String[] args) throws Exception {

    restClient();
    transportClient();

    Thread.sleep(30_000);

  }

  private static void restClient() throws IOException {
    RestClient restClient = RestClient.builder(
        new HttpHost("localhost", 9200, "http"))
        .build();

    HttpEntity entity = new NStringEntity(
        "{\n" +
            "    \"user\" : \"user\",\n" +
            "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
            "    \"message\" : \"trying out Elasticsearch\"\n" +
            "}", ContentType.APPLICATION_JSON);

    Request request = new Request("PUT", "/twitter/tweet/1");
    request.setEntity(entity);

    Response indexResponse = restClient.performRequest(request);
    System.out.println(indexResponse);

    request = new Request("PUT", "/twitter/tweet/2");
    request.setEntity(entity);

    restClient
        .performRequestAsync(request, new ResponseListener() {
          @Override
          public void onSuccess(Response response) {
          }

          @Override
          public void onFailure(Exception exception) {
          }
        });

    restClient.close();
  }

  private static void transportClient() throws Exception {

    Settings settings = Settings.builder()
        .put("cluster.name", "elasticsearch_malafes").build();

    TransportClient client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),
            9300));

    IndexRequest indexRequest = new IndexRequest("twitter").type("tweet").id("1").
        source(jsonBuilder()
            .startObject()
            .field("user", "user")
            .field("postDate", new Date())
            .field("message", "trying out Elasticsearch")
            .endObject()
        );

    IndexResponse indexResponse = client.index(indexRequest).actionGet();
    System.out.println(indexResponse);

    final CountDownLatch latch = new CountDownLatch(1);
    client.index(indexRequest, new ActionListener<IndexResponse>() {
      @Override
      public void onResponse(IndexResponse indexResponse) {
        latch.countDown();
      }

      @Override
      public void onFailure(Exception e) {
        latch.countDown();
      }
    });

    latch.await(30, TimeUnit.SECONDS);
    client.close();

  }
}
