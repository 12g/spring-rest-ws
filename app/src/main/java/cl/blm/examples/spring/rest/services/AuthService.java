package cl.blm.examples.spring.rest.services;

import cl.blm.examples.spring.rest.pojo.LoginPojo;

//TODO document this class

/**
 * Interface for a service that handles creating, validating and invalidating session tokens.
 * Should be used along with a service that stores actual Session instances.
 * 
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public interface AuthService {
  public Long identifyUser(LoginPojo credentials);

  public String generateToken(Long userId);

  public boolean validateToken(String hash);

  public boolean killToken(String hash);
}
