package cl.blm.examples.spring.rest.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import cl.blm.examples.spring.AppGlobals;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public abstract class DateUtil {
  private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

  /**
   * Parses the string as an instance of Date. Exception-safe.
   *
   * @param dateString The date string. Should be formatted like the AppGlobals.DATE_FORMAT constant.
   * 
   * @return A Date instance, or null if the string couldn't be parsed.
   */
  @Nullable
  public static final Date formatString(String dateString) {
    DateFormat formateador = new SimpleDateFormat(AppGlobals.DATE_FORMAT);
    try {
      return formateador.parse(dateString);
    } catch (Exception exc) {
      LOG.warn("Date couldn't be parsed", exc);
      return null;
    }
  }

  /**
   * Formats the Date into a string, applying the AppGlobals.DATE_FORMAT constant.
   *
   * @param date The date instance. Can be null
   * 
   * @return A formatted date string, or null if the specified date is null.
   */
  @Nullable
  public static final String formatDate(Date date) {
    if (date != null) {
      DateFormat formateador = new SimpleDateFormat(AppGlobals.DATE_FORMAT);
      return formateador.format(date);
    } else {
      return null;
    }
  }
}
