package cl.blm.examples.spring.conversion;

import cl.blm.examples.spring.rest.model.dto.UserDto;
import cl.blm.examples.spring.rest.model.entities.User;
import cl.blm.examples.spring.rest.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Component
public class UserDto2Entity
    implements Converter<UserDto, User> {
  @Override
  public User convert(UserDto source) {
    User target = new User();

    if (source.getUserId() != null) {
      target.setId(source.getUserId());
    }

    if (source.getUserName() == null) {
      target.setName(source.getUserName());
    }

    if (source.getUserRegistrationDate() == null) {
      Date now = Calendar.getInstance().getTime();
      target.setRegistrationDate(now);
    } else {
      Date regDate = DateUtil.formatString(source.getUserRegistrationDate());
      target.setRegistrationDate(regDate);
    }

    return target;
  }
}
