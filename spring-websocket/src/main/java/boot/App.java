package boot;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import util.Util;

@SpringBootApplication
public class App {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  private static final String SEND_HELLO_MESSAGE_ENDPOINT = "/app/hello";
  private static final String SUBSCRIBE_GREETINGS_ENDPOINT = "/topic/greetings";

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      String url = "ws://localhost:8080/test-websocket";

      WebSocketStompClient stompClient = new WebSocketStompClient(
          new SockJsClient(createTransportClient()));
      stompClient.setMessageConverter(new MappingJackson2MessageConverter());

      StompSession stompSession = stompClient.connect(url, new StompSessionHandlerAdapter() {
      }).get(1, TimeUnit.SECONDS);

      stompSession.subscribe(SUBSCRIBE_GREETINGS_ENDPOINT, new GreetingStompFrameHandler());
      stompSession.send(SEND_HELLO_MESSAGE_ENDPOINT, "Hello");

      TimeUnit.SECONDS.sleep(10);
      Util.checkSpan("websocket", 6);

      System.exit(0);
    };
  }

  private static List<Transport> createTransportClient() {
    return Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
  }

  private class GreetingStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
      return String.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
    }
  }

}
