package mongoasync;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import java.util.concurrent.TimeUnit;
import org.bson.Document;

public class App {

  public static void main(String[] args) throws InterruptedException {
    final MongoClient client = createAsyncClient();

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

    TimeUnit.SECONDS.sleep(30);

    client.close();

  }


  private static MongoClient createAsyncClient() {
    return MongoClients.create();

//    MongoClientSettings settings = MongoClientSettings.builder()
//        .applyConnectionString(
//            new ConnectionString("mongodb://localhost:27017"))
//        .build();
//
//
//    return MongoClients.create(MongoClientSettings.builder(settings).build());
  }
}
