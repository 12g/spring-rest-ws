package cl.blm.examples.spring.rest.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import cl.blm.examples.spring.AppGlobals;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public abstract class HashUtil {
  private static final Logger LOG = LoggerFactory.getLogger(HashUtil.class);

  /**
   * Standard one-way cryptography.
   *
   * @author user1452273 at stackoverflow.com
   * 
   * @param data
   *
   * @return
   */
  @Nullable
  public static String hash(String data) {
    LOG.debug("hash");
    try {
      byte[] payload = data.getBytes(Charset.forName(AppGlobals.CRYPTOGRAPHIC_CHARSET));

      MessageDigest crypto = MessageDigest.getInstance(AppGlobals.CRYPTOGRAPHIC_ALGORITHM);
      byte[] rawHash = crypto.digest(payload);

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < rawHash.length; i++) {
        String hex = Integer.toHexString(0xff & rawHash[i]);
        if (hex.length() == 1) {
          sb.append('0');
        }
        sb.append(hex);
      }

      return sb.toString();
    } catch (Exception ex) {
      LOG.error("Couldn't hash input data", ex);
      return null;
    }
  }

  /**
   * Adds salt to, and then hashes the data.
   *
   * @param data
   *
   * @return
   */
  public static String encrypt(String data) {
    LOG.debug("encrypt");
    return hash(AppGlobals.CRYPTOGRAPHIC_SALT + data);
  }
}
