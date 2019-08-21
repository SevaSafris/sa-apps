package boot;

import com.rabbitmq.client.Channel;
import io.opentracing.util.GlobalTracer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.Util;

@SpringBootApplication
public class App {
  static final String topicExchangeName = "spring-boot-exchange";

  static final String queueName = "queue-1";
  static final String queueName2 = "queue-2";

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  public Queue queue2() {
    return new Queue(queueName2);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    final CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setHost("localhost");
    factory.setPort(broker.getBrokerPort());
    return factory;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage") {
      @Override
      public void onMessage(Message message, Channel channel) throws Exception {
        if (GlobalTracer.get().activeSpan() == null) {
          System.err.println("Missing active span");
          System.exit(-1);
        }
        System.out.println("Active span: " + GlobalTracer.get().activeSpan());
        super.onMessage(message, channel);
      }
    };
  }

  @RabbitListener(queues = queueName2)
  public void listen(String message) {
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("Missing active span");
      System.exit(-1);
    }
    System.out.println("Active span: " + GlobalTracer.get().activeSpan());
    System.out.println("Received <" + message + ">");
  }

  private static EmbeddedAMQPBroker broker;

  public static void main(String[] args) throws Exception {
    broker = new EmbeddedAMQPBroker();

    SpringApplication.run(App.class, args).close();

    broker.shutdown();
    Util.checkSpan("java-rabbitmq", 6);
  }

}

