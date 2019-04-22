package mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.concurrent.TimeUnit;
import org.bson.Document;

public class App {
  public static void main(String[] args) throws Exception {
    final com.mongodb.client.MongoClient client = createClient();

    // This one doesn't work:
    // MongoClient mongoClient = new MongoClient("localhost", 27017);

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

    TimeUnit.SECONDS.sleep(30);
  }


  private static MongoClient createClient() {
//    if(true) {
//      return MongoClients.create();
//    }

    final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build();

    return MongoClients.create(mongoClientSettings);



  }


}
