package mongoreactive;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class App {
  public static void main(String[] args) throws InterruptedException {
    MongoClient mongoClient = MongoClients.create();
    CountDownLatch latch = new CountDownLatch(1);
    final MongoCollection<Document> collection = mongoClient.getDatabase("MyDB").getCollection("MyCollection");
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
    mongoClient.close();

    TimeUnit.SECONDS.sleep(30);

  }
}
