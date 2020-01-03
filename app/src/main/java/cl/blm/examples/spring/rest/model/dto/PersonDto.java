package cl.blm.examples.spring.rest.model.dto;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
public class PersonDto {
  protected Long personId;
  protected String personFullName;
  protected String personIdNumber;
  protected String personAddress;
  protected String personEmail;
  protected Collection<Long> personPhones;

  public PersonDto() {
    super();
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public String getPersonFullName() {
    return personFullName;
  }

  public void setPersonFullName(String personFullName) {
    this.personFullName = personFullName;
  }

  public String getPersonIdNumber() {
    return personIdNumber;
  }

  public void setPersonIdNumber(String personIdNumber) {
    this.personIdNumber = personIdNumber;
  }

  public String getPersonAddress() {
    return personAddress;
  }

  public void setPersonAddress(String personAddress) {
    this.personAddress = personAddress;
  }

  public String getPersonEmail() {
    return personEmail;
  }

  public void setPersonEmail(String personEmail) {
    this.personEmail = personEmail;
  }

  public Collection<Long> getPersonPhones() {
    if (personPhones != null) {
      return new ArrayList<>(personPhones);
    } else {
      return new ArrayList<>();
    }
  }

  public void setPersonPhones(Collection<Long> personPhones) {
    if (personPhones != null) {
      this.personPhones = new ArrayList<>(personPhones);
    }
  }

  @Override
  public String toString() {
    return "PersonDto{" + "personId=" + personId + ", personFullName=" + personFullName + ", personIdNumber=" + personIdNumber
        + ", personAddress=" + personAddress + ", personEmail=" + personEmail + ", personPhones=" + personPhones + '}';
  }
}
