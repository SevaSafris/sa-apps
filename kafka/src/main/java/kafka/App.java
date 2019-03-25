package kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class App {
  public static void main(String[] args) throws InterruptedException {
    final Producer<Long, String> producer = createProducer();

    for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
      ProducerRecord<Long, String> record = new ProducerRecord<>(
          IKafkaConstants.TOPIC_NAME, "This is record " + index);
      producer.send(record, new Callback() {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
          System.out.println(this.getClass().getName());
        }
      });
    }

    createConsumer();
    producer.close();

    Thread.sleep(30_000);
  }

  private static Producer<Long, String> createProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
    props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return new KafkaProducer<>(props);
  }

  private static void createConsumer() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      Properties props = new Properties();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
      props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
          StringDeserializer.class.getName());
      props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
      props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);
      Consumer<Long, String> consumer = new KafkaConsumer<>(props);
      consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));

      int count = 0;
      while (count < IKafkaConstants.MESSAGE_COUNT) {
        ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<Long, String> record : records) {
          System.out.println(record);
          consumer.commitSync();
          count++;
          System.out.println(count);
          //System.out.println(record.headers());
        }
      }
      consumer.close();
    });

  }

  public interface IKafkaConstants {
    String KAFKA_BROKERS = "localhost:9092";
    Integer MESSAGE_COUNT = 5;
    String CLIENT_ID = "client1";
    String TOPIC_NAME = "demo";
    String GROUP_ID_CONFIG = "consumerGroup1";
    String OFFSET_RESET_EARLIER = "earliest";
    Integer MAX_POLL_RECORDS = 1;
  }
}
