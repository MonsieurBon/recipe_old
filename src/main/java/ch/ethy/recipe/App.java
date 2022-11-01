package ch.ethy.recipe;

import io.undertow.*;
import io.undertow.util.*;

public class App {
  public static void main(String[] args) {
    Undertow server = Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler(exchange -> {
          exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
          exchange.getResponseSender().send("Hello World!");
        }).build();

    server.start();
  }
}
