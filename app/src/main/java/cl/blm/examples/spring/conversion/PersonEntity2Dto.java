package cl.blm.examples.spring.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.blm.examples.spring.rest.model.dto.PersonDto;
import cl.blm.examples.spring.rest.model.entities.Person;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Component
public class PersonEntity2Dto
        implements Converter<Person, PersonDto> {
  @Override
  public PersonDto convert(Person source) {
    PersonDto target = new PersonDto();
    target.setPersonId(source.getId());
    target.setPersonFullName(source.getFullName());
    target.setPersonIdNumber(source.getIdNumber());
    
    String address = source.getAddress();
    if (address != null && !address.isEmpty()) {
      target.setPersonAddress(address);
    }
    
    String email = source.getEmail();
    if (email != null && !email.isEmpty()) {
      target.setPersonEmail(email);
    }
    
    return target;
  }
}
