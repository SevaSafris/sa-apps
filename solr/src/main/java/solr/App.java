package solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;

public class App {
  private static final String solrUrl = "http://localhost:8983/solr";

  public static void main(String[] args)
      throws IOException, SolrServerException, InterruptedException {

    SolrClient client = getHttpClient();

    final Map<String, String> queryParamMap = new HashMap<>();
    queryParamMap.put("q", "*:*");
    queryParamMap.put("fl", "id, name");
    queryParamMap.put("sort", "id asc");
    MapSolrParams queryParams = new MapSolrParams(queryParamMap);

    final QueryResponse response = client.query("gettingstarted", queryParams);
    final SolrDocumentList documents = response.getResults();
    System.out.println(documents.toString());

    TimeUnit.SECONDS.sleep(30);
  }

  private static SolrClient getHttpClient() {
    return new HttpSolrClient.Builder(solrUrl).build();
  }
}
