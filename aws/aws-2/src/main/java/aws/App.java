package aws;


import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    if (System.getProperty("user.dir").contains("aws")) {
      System.getProperties().setProperty("sqlite4java.library.path", "src/main/resources/libs");
    } else {
      System.getProperties()
          .setProperty("sqlite4java.library.path", "aws-2/src/main/resources/libs");
    }

    final String[] localArgs = {"-inMemory", "-port", "8000"};
    DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(localArgs);
    server.start();

    DynamoDbClient dbClient = buildClient();

    try {
      createTable(dbClient, "tableName-" + ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage() + "\nIgnoring.");
    }
    server.stop();
    Util.checkSpan("java-aws-sdk", 1);
    System.exit(0);
  }

  private static DynamoDbClient buildClient() {
    AwsSessionCredentials awsCreds = AwsSessionCredentials.create(
        "access_key_id",
        "secret_key_id",
        "session_token");

    return DynamoDbClient.builder().endpointOverride(URI.create("http://localhost:8000"))
        .region(Region.US_WEST_2)
        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
        .overrideConfiguration(
            ClientOverrideConfiguration.builder().apiCallTimeout(Duration.ofSeconds(1)).build())
        .build();

//    final AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
//        "http://localhost:8000", "us-west-2");
//    final BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
//    final AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder
//        .standard()
//        .withEndpointConfiguration(endpointConfiguration)
//        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//        .withClientConfiguration(new ClientConfiguration().withConnectionTimeout(1));
//
//    return builder.build();
  }

  private static void createTable(final DynamoDbClient dbClient, final String tableName) {
    final String partitionKeyName = tableName + "-pk";
    final CreateTableRequest createTableRequest = CreateTableRequest.builder()
        .tableName(tableName)
        .keySchema(
            KeySchemaElement.builder().attributeName(partitionKeyName).keyType(KeyType.HASH)
                .build())
        .attributeDefinitions(
            AttributeDefinition.builder().attributeName(partitionKeyName).attributeType("S")
                .build())
        .provisionedThroughput(
            ProvisionedThroughput.builder().readCapacityUnits(10L).writeCapacityUnits(5L).build())
        .build();

    dbClient.createTable(createTableRequest);
    System.out.println("Table " + tableName + " created");
  }
}
