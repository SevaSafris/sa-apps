package es7;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import util.Util;

public class App {
  private static final int HTTP_PORT = 9205;
  private static final String HTTP_TRANSPORT_PORT = "9305";
  private static final String ES_WORKING_DIR = "target/es";
  private static String clusterName = "cluster-name";
  private static Node node;

  public static void main(String[] args) throws Exception {
    Settings settings = Settings.builder()
        .put("path.home", ES_WORKING_DIR)
        .put("path.data", ES_WORKING_DIR + "/data")
        .put("path.logs", ES_WORKING_DIR + "/logs")
        .put("transport.type", "netty4")
        .put("http.type", "netty4")
        .put("cluster.name", clusterName)
        .put("http.port", HTTP_PORT)
        .put("transport.tcp.port", HTTP_TRANSPORT_PORT)
        .put("network.host", "127.0.0.1")
        .build();
    Collection plugins = Collections.singletonList(Netty4Plugin.class);
    node = new PluginConfigurableNode(settings, plugins);
    node.start();

    restClient();
    transportClient();

    node.close();

    Util.checkSpan("java-elasticsearch", 3);
    System.exit(0);
  }

  private static void restClient() throws IOException {
    RestClient restClient = RestClient.builder(
        new HttpHost("localhost", HTTP_PORT, "http"))
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
        .put("cluster.name", clusterName).build();

    TransportClient client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),
            Integer.parseInt(HTTP_TRANSPORT_PORT)));

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

  private static class PluginConfigurableNode extends Node {

    public PluginConfigurableNode(Settings settings,
        Collection<Class<? extends Plugin>> classpathPlugins) {
      super(InternalSettingsPreparer
              .prepareEnvironment(settings, new HashMap<>(), null, () -> "local"),
          classpathPlugins, false);
    }

  }
}
