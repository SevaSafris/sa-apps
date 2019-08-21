package kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import util.Util;

public class App {
  private static Integer MESSAGE_COUNT = 5;
  private static String TOPIC_NAME = "demo";

  public static void main(String[] args) throws Exception {
    final EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(2, true, 2, "messages");
    embeddedKafkaRule.before();

    final Producer<Long, String> producer = createProducer(embeddedKafkaRule);

    for (int index = 0; index < MESSAGE_COUNT; index++) {
      ProducerRecord<Long, String> record = new ProducerRecord<>(
          TOPIC_NAME, "This is record " + index);
      producer.send(record, new Callback() {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
          System.out.println(this.getClass().getName());
        }
      });
    }

    createConsumer(embeddedKafkaRule);
    producer.close();

    TimeUnit.SECONDS.sleep(10);
    embeddedKafkaRule.after();
    Util.checkSpan("java-kafka", 10);

    System.exit(0);
  }

  private static Producer<Long, String> createProducer(
      EmbeddedKafkaRule embeddedKafkaRule) {
    final Map<String, Object> senderProps = KafkaTestUtils
        .producerProps(embeddedKafkaRule.getEmbeddedKafka());
    return new KafkaProducer<>(senderProps);
  }

  private static void createConsumer(
      EmbeddedKafkaRule embeddedKafkaRule) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      final Map<String, Object> consumerProps = KafkaTestUtils
          .consumerProps("sampleRawConsumer", "false", embeddedKafkaRule.getEmbeddedKafka());
      consumerProps.put("auto.offset.reset", "earliest");
      Consumer<Long, String> consumer = new KafkaConsumer<>(consumerProps);
      consumer.subscribe(Collections.singletonList(TOPIC_NAME));

      int count = 0;
      while (count < MESSAGE_COUNT) {
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

}
