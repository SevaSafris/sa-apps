package boot;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  private final RestClient restClient;
  private int counter;

  @Autowired
  public ScheduledTask(RestClient restClient) {
    this.restClient = restClient;
  }

  @Scheduled(fixedDelay = 5000)
  public void task() {
    try {
      makeRequest();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
    counter++;
    if (counter == 5) {
      System.exit(0);
    }
  }

  private void makeRequest() throws IOException {
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
  }
}
