package cl.blm.examples.spring.rest.pojo;

/**
 * Simple wrapper class for login credentials.
 * 
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public class LoginPojo {
  public String username;
  public String password;

  public boolean credentialsAreNotEmpty() {
    return (username != null && !username.isEmpty() && password != null && !password.isEmpty());
  }
}