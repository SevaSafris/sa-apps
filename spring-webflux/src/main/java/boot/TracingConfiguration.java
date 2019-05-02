package boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
class TracingConfiguration {
  @Bean
  public WebFilter tracingWebFilter() {
    return new WebFilter() {
      @Override
      public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("EEEE");
        return chain.filter(exchange);
      }
    };
  }
}
