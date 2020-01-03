package cl.blm.examples.spring.rest.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cl.blm.examples.spring.rest.model.entities.Phone;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Component
@Repository
public interface PhoneRepository
    extends JpaRepository<Phone, Long>, QuerydslPredicateExecutor<Phone> {
}
