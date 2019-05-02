package boot;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.CompositeRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;

@Configuration
public class Config {

  @Bean(name="httpClient")
  public HttpClient getHttpClient() {
    return HttpClientBuilder.create()
        .build();
  }

  @Bean
  public RetryOperationsInterceptor retryInterceptor() {
    TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
    timeoutRetryPolicy.setTimeout(1000);
    SimpleRetryPolicy attemptsRetryPolicy = new SimpleRetryPolicy();
    attemptsRetryPolicy.setMaxAttempts(3);
    CompositeRetryPolicy retryPolicy = new CompositeRetryPolicy();
    retryPolicy.setPolicies(new RetryPolicy[] {timeoutRetryPolicy, attemptsRetryPolicy});

    return RetryInterceptorBuilder
        .stateless().retryPolicy(retryPolicy).backOffOptions(200, 1, 500).build();
  }
}
