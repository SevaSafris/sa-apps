package mongoasync;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterSettings.Builder;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {
    final MongoServer server = new MongoServer(new MemoryBackend());
    final InetSocketAddress serverAddress = server.bind();

    final MongoClient client = createAsyncClient(serverAddress);

    com.mongodb.async.client.MongoDatabase database = client.getDatabase("myMongoDb");
    if (database.getCollection("customers") == null) {
      database.createCollection("customers", (result, t) -> System.out.println("created"));
    }
    com.mongodb.async.client.MongoCollection<Document> collection = database
        .getCollection("customers");
    Document document = new Document();
    document.put("name", "Name");
    document.put("company", "Company");
    collection.insertOne(document, (result, t) -> System.out.println("result"));

    TimeUnit.SECONDS.sleep(10);

    client.close();
    server.shutdownNow();

    Util.checkSpan("java-mongo", 1);
  }


  private static MongoClient createAsyncClient(final InetSocketAddress serverAddress) {
    final MongoClientSettings clientSettings = MongoClientSettings.builder()
        .applyToClusterSettings(new Block<Builder>() {
          @Override
          public void apply(final ClusterSettings.Builder builder) {
            builder.hosts(Arrays.asList(new ServerAddress(serverAddress)));
          }
        }).build();

    return MongoClients.create(clientSettings);

//    MongoClientSettings settings = MongoClientSettings.builder()
//        .applyConnectionString(
//            new ConnectionString("mongodb://localhost:27017"))
//        .build();
//
//
//    return MongoClients.create(MongoClientSettings.builder(settings).build());
  }
}
