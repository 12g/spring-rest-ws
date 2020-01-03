package cl.blm.examples.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@PropertySource("file:custom.properties")
public abstract class AppGlobals {
  @Value("${items.per_page}") public static int ITEMS_PER_PAGE;

  @Value("${date.format}") public static String DATE_FORMAT;

  @Value("${crypto.algorithm}") public static String CRYPTOGRAPHIC_ALGORITHM;

  @Value("${crypto.salt}") public static String CRYPTOGRAPHIC_SALT;

  @Value("${crypto.charset}") public static String CRYPTOGRAPHIC_CHARSET;

  @Value("${session.lifetime}") public static long SESSION_LIFETIME;
}
