package cl.blm.examples.spring.rest.model.dto;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public class UserDto {
  private Long userId;
  private String userName;
  private String userRegistrationDate;
  private PersonDto person;

  public UserDto() {
    super();
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserRegistrationDate() {
    return userRegistrationDate;
  }

  public void setUserRegistrationDate(String userRegistrationDate) {
    this.userRegistrationDate = userRegistrationDate;
  }

  public PersonDto getPerson() {
    return person;
  }

  public void setPerson(PersonDto person) {
    this.person = person;
  }

  @Override
  public String toString() {
    return "UserDto{" + "userId=" + userId + ", userName=" + userName + ", userRegistrationDate=" + userRegistrationDate + ", person=" + person + '}';
  }
}
