package jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class App {
  public static void main(String[] args) {

    final Client client = ClientBuilder.newClient();
    final Response response = client.target("http://www.google.com")
        .request(MediaType.TEXT_PLAIN)
        .get();
    System.out.println(response.getStatus());

  }
}
