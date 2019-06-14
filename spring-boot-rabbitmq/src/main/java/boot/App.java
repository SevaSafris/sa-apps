package boot;

import com.rabbitmq.client.Channel;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import java.util.Map.Entry;
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
    factory.setVirtualHost("/");
    return factory;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage") {
      @Override
      public void onMessage(Message message, Channel channel) throws Exception {
        for (Entry<String, Object> entry : message.getMessageProperties().getHeaders()
            .entrySet()) {
          System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        final Tracer tracer = GlobalTracer.get();
        System.out.println(tracer.scopeManager().active());
        System.out.println("onMessage START");
        super.onMessage(message, channel);
        System.out.println("onMessage End");
      }
    };
  }

  @RabbitListener(queues = queueName2)
  public void listen(String message) {
    System.out.println("Received <" + message + ">");
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args).close();
  }

}

