package cl.blm.examples.spring.rest.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Configuration
@EntityScan(basePackages = { "cl.blm.examples.spring.rest.model.entities" })
@EnableJpaRepositories(basePackages = { "cl.blm.spring.rest.model.repositories" })
@PropertySources({ @PropertySource("file:jpa.properties"), @PropertySource("file:datasource.properties") })
public class JpaConfig {
}
