package aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import java.util.concurrent.ThreadLocalRandom;

public class App {
  public static void main(String[] args) throws Exception {
    AmazonDynamoDB dbClient = buildClient();

    long start = System.currentTimeMillis();
    for (int i = 0; i < 100; i++) {
      createTable(dbClient, "tableName-" + ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
    }
    long end = System.currentTimeMillis();

    double duration = (end - start) / 1000.0;
    System.out.println("Duration: " + duration + " sec");
    Thread.sleep(30_000L);


  }

  private static AmazonDynamoDB buildClient() {
    final AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
        "http://localhost:8000", "us-west-2");
    final BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
    final AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder
        .standard()
        .withEndpointConfiguration(endpointConfiguration)
        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
        .withClientConfiguration(new ClientConfiguration().withConnectionTimeout(1));

    return builder.build();
  }

  private static void createTable(final AmazonDynamoDB dbClient, final String tableName) {
    final String partitionKeyName = tableName + "-pk";
    final CreateTableRequest createTableRequest = new CreateTableRequest()
        .withTableName(tableName)
        .withKeySchema(
            new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH))
        .withAttributeDefinitions(
            new AttributeDefinition().withAttributeName(partitionKeyName).withAttributeType("S"))
        .withProvisionedThroughput(
            new ProvisionedThroughput().withReadCapacityUnits(10L).withWriteCapacityUnits(5L));

    dbClient.createTable(createTableRequest);
    System.out.println("Table " + tableName + " created");
  }
}
