package ch.ethy.recipe;

import io.undertow.*;
import io.undertow.server.*;
import io.undertow.server.handlers.*;
import io.undertow.server.handlers.error.*;
import io.undertow.server.handlers.resource.*;
import java.net.*;
import java.nio.file.*;

public class App {

  public static void main(String[] args) throws Exception {
    App app = new App();
    app.start();
  }

  private void start() {
    Undertow server = Undertow
      .builder()
      .addHttpListener(8080, "0.0.0.0")
      .setHandler(getHandler())
      .build();

    server.start();
  }

  private HttpHandler getHandler() {
    URL indexResource = App.class.getResource("public/index.html");
    HttpHandler fileNotFoundHandler = ResponseCodeHandler.HANDLE_404;
    if (indexResource != null) {
      try {
        Path index = Path.of(indexResource.toURI());
        fileNotFoundHandler =
          new FileErrorPageHandler(ResponseCodeHandler.HANDLE_200, index, 200);
      } catch (URISyntaxException e) {
        System.out.println("Failed to create fallback resource handler");
      }
    }

    ResourceHandler resourceHandler = new ResourceHandler(
      new ClassPathResourceManager(App.class.getClassLoader(), "public"),
      fileNotFoundHandler
    );

    return Handlers.path(resourceHandler);
  }
}
