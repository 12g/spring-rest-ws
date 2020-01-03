package cl.blm.examples.spring.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.blm.examples.spring.rest.model.dto.PersonDto;
import cl.blm.examples.spring.rest.model.dto.SessionDto;
import cl.blm.examples.spring.rest.model.dto.UserDto;
import cl.blm.examples.spring.rest.pojo.LoginPojo;
import cl.blm.examples.spring.rest.services.AuthService;
import cl.blm.examples.spring.rest.services.CrudService;

/**
 * API point of entry for anything session-related.
 * 
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class SessionsController {
  private final static Logger LOG = LoggerFactory.getLogger(SessionsController.class);

  final String MSG_EXPIRED = "Invalid/expired session token";

  @Autowired private AuthService authSvc;
  @Autowired private CrudService<SessionDto, String> sessionSvc;
  @Autowired private CrudService<UserDto, Long> userSvc;

  /**
   * Opens a new user session.
   *
   * @param credentials
   *
   * @return
   */
  @PostMapping("/session")
  public ResponseEntity<Boolean> openSession(@RequestBody LoginPojo credentials) {
    LOG.info("open");
    LOG.debug("credentials={}", credentials);
    if (credentials != null && credentials.credentialsAreNotEmpty()) {
      LOG.info("Authenticating...");
      Long userId = authSvc.identifyUser(credentials);
      if (userId != null) {
        UserDto user = userSvc.find(userId);
        String token = authSvc.generateToken(userId);

        SessionDto session = new SessionDto();
        session.setUser(user);
        session.setSessionHash(token);
        session = sessionSvc.create(session);
        boolean result = (session != null);
        return ResponseEntity.ok(result);
      }
    }
    LOG.info("Invalid request data");
    return ResponseEntity.ok(false);
  }

  /**
   * Validates a given session token.
   *
   * @param token
   *
   * @return El ID de la sesion.
   */
  @GetMapping("/session")
  public ResponseEntity<Boolean> validateSessionToken(@RequestHeader("Authorization") String token) {
    LOG.info("validateSessionToken");
    LOG.debug("token={}", token);
    if (token != null && !token.isEmpty()) {
      boolean validated = authSvc.validateToken(token);
      return ResponseEntity.ok(validated);
    }
    LOG.info(MSG_EXPIRED);
    return ResponseEntity.ok(false);
  }

  /**
   * Closes any session identified by a session token.
   *
   * @param token
   *
   * @return Siempre devuelve true, por motivos de seguridad.
   */
  @DeleteMapping("/session")
  public boolean closeSessionFromToken(@RequestHeader("Authorization") String token) {
    LOG.info("closeFromToken");
    LOG.debug("token={}", token);
    if (token != null && !token.isEmpty()) {
      authSvc.killToken(token);
    } else {
      return false;
    }
    return true;
  }

  /**
   * Retrieves profile data for the user account associated to the provided session token.
   *
   * @param token
   *
   * @return A PersonDto object with the profile data.
   */
  @GetMapping("/session/profile")
  public PersonDto getProfile(
          @RequestParam("hash") String token
  ) {
    LOG.info("getProfile");
    if (token != null && !token.isEmpty()) {
      LOG.debug("token={}", token);
      SessionDto session = sessionSvc.find(token);
      if (session != null) {
        UserDto user = session.getUser();
        PersonDto person = userSvc.find(user.getUserId()).getPerson();
        return person;
      }
    }
    LOG.info(MSG_EXPIRED);
    return null;
  }
}
