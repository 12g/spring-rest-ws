package cl.blm.examples.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.blm.examples.spring.rest.RestConfig;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@SpringBootApplication
@PropertySources({@PropertySource("file:logging.properties")})
public class App {
  /**
   * Application starting point.
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(RestConfig.class, args);
  }

}
