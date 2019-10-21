package mongoreactive;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterSettings.Builder;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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

    MongoClient mongoClient = MongoClients.create(mongoSettings);
    CountDownLatch latch = new CountDownLatch(1);
    final MongoCollection<Document> collection = mongoClient.getDatabase("MyDB")
        .getCollection("MyCollection");
    final Document myDocument = new Document("name", "MyDocument");
    collection.insertOne(myDocument).subscribe(new Subscriber<Success>() {
      @Override
      public void onSubscribe(Subscription s) {
        s.request(1);
      }

      @Override
      public void onNext(Success success) {

      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onComplete() {
        latch.countDown();
      }
    });
    latch.await(15, TimeUnit.SECONDS);

    Util.checkSpan("java-mongo", 1);

    mongoClient.close();
    server.shutdownNow();
  }
}
