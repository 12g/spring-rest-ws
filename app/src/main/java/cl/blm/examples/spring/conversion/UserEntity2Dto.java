package cl.blm.examples.spring.conversion;

import cl.blm.examples.spring.rest.model.dto.UserDto;
import cl.blm.examples.spring.rest.model.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Component
public class UserEntity2Dto
        implements Converter<User, UserDto> {
  @Override
  public UserDto convert(User source) {
    UserDto target = new UserDto();
    target.setUserId(source.getId());
    target.setUserName(source.getName());
    return target;
  }
}
