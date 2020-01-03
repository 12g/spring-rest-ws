package cl.blm.examples.spring.rest.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import cl.blm.examples.spring.AppGlobals;
import cl.blm.examples.spring.rest.model.entities.QSession;
import cl.blm.examples.spring.rest.model.entities.Session;
import cl.blm.examples.spring.rest.model.entities.User;
import cl.blm.examples.spring.rest.model.repositories.SessionRepository;
import cl.blm.examples.spring.rest.model.repositories.UserRepository;
import cl.blm.examples.spring.rest.pojo.LoginPojo;
import cl.blm.examples.spring.rest.services.AuthService;
import cl.blm.examples.spring.rest.util.DateUtil;
import cl.blm.examples.spring.rest.util.HashUtil;

//TODO fix method logs to debug their execution parameters

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Service
public class AuthServiceImpl
    implements AuthService {
  private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

  @Autowired private UserRepository users;
  @Autowired private SessionRepository sessions;

  private String sessionPayloadForUser(User user) {
    LOG.info("sessionPayload");
    String currentDateTime = DateUtil.formatDate(Calendar.getInstance().getTime());
    return new StringBuilder().append("[").append(user.toString()).append(" connected at ").append(currentDateTime).append("]").toString();
  }

  private Predicate filterSessionsWhereNotClosedAndMatchingToken(String token) {
    QSession qSession = QSession.session;
    BooleanBuilder matchingToken = new BooleanBuilder().and(qSession.hash.eq(token));
    BooleanBuilder sessionsNotClosed = new BooleanBuilder().and(qSession.closeDate.isNull());
    return new BooleanBuilder().and(matchingToken).and(sessionsNotClosed);
  }

  @Override
  public Long identifyUser(LoginPojo credentials) {
    LOG.info("login");
    BooleanBuilder byCredentials = new BooleanBuilder();
    Optional<User> userQuery = users.findOne(byCredentials);
    if (userQuery.isPresent()) {
      return userQuery.get().getId();
    } else {
      return null;
    }
  }

  @Override
  public String generateToken(Long userId) {
    LOG.info("generateToken");
    Optional<User> userQuery = users.findById(userId);

    if (!userQuery.isPresent()) {
      return null;
    } else {
      String sessionData = this.sessionPayloadForUser(userQuery.get());
      return HashUtil.encrypt(sessionData);
    }
  }

  @Override
  public boolean validateToken(String token) {
    LOG.info("validateToken");
    Date now = Calendar.getInstance().getTime();
    long nowMs = now.getTime();
    boolean thereIsAnotherActiveSession = false;

    Predicate notClosedAndMatchingToken = this.filterSessionsWhereNotClosedAndMatchingToken(token);
    Iterable<Session> itrSessions = sessions.findAll(notClosedAndMatchingToken);
    for (Session ssn : itrSessions) {
      long expirationDateMs = ssn.getOpenDate().getTime() + AppGlobals.SESSION_LIFETIME;
      boolean isSessionExpired = (expirationDateMs < nowMs);
      if (isSessionExpired) {
        ssn.setCloseDate(now);
        sessions.save(ssn);
      } else {
        // if there are many open sessions, just leave the first one open, first one to be closed
        if (!thereIsAnotherActiveSession) {
          thereIsAnotherActiveSession = true;
        } else {
          ssn.setCloseDate(now);
          sessions.save(ssn);
        }
      }
    }
    sessions.flush();
    return thereIsAnotherActiveSession;
  }

  @Override
  public boolean killToken(String token) {
    Date now = Calendar.getInstance().getTime();
    Predicate notClosedAndMatchingToken = this.filterSessionsWhereNotClosedAndMatchingToken(token);
    Iterable<Session> itrSessions = sessions.findAll(notClosedAndMatchingToken);
    for (Session session : itrSessions) {
      session.setCloseDate(now);
      sessions.save(session);
    }
    sessions.flush();
    return true;
  }

}
