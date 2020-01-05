package cl.blm.examples.spring.rest.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import cl.blm.examples.spring.rest.model.entities.Session;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Repository
public interface SessionRepository
    extends JpaRepository<Session, String>, QuerydslPredicateExecutor<Session> {
}
