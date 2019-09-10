package mongo;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterSettings.Builder;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.net.InetSocketAddress;
import java.util.Arrays;
import org.bson.Document;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final MongoServer server = new MongoServer(new MemoryBackend());
    final InetSocketAddress serverAddress = server.bind();

    final MongoClientSettings mongoSettings = MongoClientSettings.builder()
        .applyToClusterSettings(new Block<Builder>() {
          @Override
          public void apply(final ClusterSettings.Builder builder) {
            builder.hosts(Arrays.asList(new ServerAddress(serverAddress)));
          }
        }).build();

    final com.mongodb.client.MongoClient client = MongoClients.create(mongoSettings);

    MongoDatabase database = client.getDatabase("myMongoDb");
    if (database.getCollection("customers") == null) {
      database.createCollection("customers");
    }
    MongoCollection<Document> collection = database.getCollection("customers");
    Document document = new Document();
    document.put("name", "Name");
    document.put("company", "Company");
    collection.insertOne(document);
    collection.find().first();

    client.close();
    server.shutdownNow();

    Util.checkSpan("java-mongo", 2);
  }
}
