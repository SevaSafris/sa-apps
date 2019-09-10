package boot;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import util.Util;

@SpringBootApplication
public class App {
  private static EmbeddedKafkaBroker kafkaEmbedded;

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(KafkaTestUtils.producerProps(kafkaEmbedded));
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    final Map<String, Object> consumerProps = KafkaTestUtils
        .consumerProps("sampleRawConsumer", "false", kafkaEmbedded);
    consumerProps.put("auto.offset.reset", "earliest");
    return new DefaultKafkaConsumerFactory<>(consumerProps);
  }

  @Bean
  public KafkaListenerContainerFactory kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setReplyTemplate(kafkaTemplate());
    return factory;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  public static void main(String[] args) throws Exception {
    final EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1, true, 2, "users", "reply");
    embeddedKafkaRule.before();
    kafkaEmbedded = embeddedKafkaRule.getEmbeddedKafka();

    SpringApplication.run(App.class, args).close();
    Util.checkSpan("java-kafka", 6);
    System.exit(0);
  }

}
