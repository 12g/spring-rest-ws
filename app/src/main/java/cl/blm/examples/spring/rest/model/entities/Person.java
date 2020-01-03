package cl.blm.examples.spring.rest.model.entities;

import cl.blm.examples.spring.rest.model.EntityGlobals;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import java.util.Collection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Persistence class for items of the table PEOPLE.
 * A 'person' is a group of personal information about a living individual.
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Entity
@Table(name = "PEOPLE")
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p") })
public class Person
    implements Serializable {
  private static final long serialVersionUID = -3959798628396766146L;

//@formatter:off
  @Id
  @Column(name = "PERSON_ID")
  private Long id;

  @NonNull
  @Size(min = 1, max = 150)
  @Column(name = "PERSON_FULL_NAME")
  private String fullName;

  @NonNull
  @Size(min = 1, max = 20)
  @Column(name = "PERSON_ID_NUMBER")
  private String idNumber;

  @Size(min = 8, max = 100)
  @Pattern(regexp = EntityGlobals.EMAIL_REGEX, message = "Invalid email")
  @Column(name = "PERSON_EMAIL")
  private String email;

  @Size(max = 200)
  @Column(name = "PERSON_ADDRESS")
  private String address;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "PERSON_ID")
  private Collection<Phone> phones;

  public Person() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Collection<Phone> getPhones() {
    return phones;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Person other = (Person) obj;
    if (address == null) {
      if (other.address != null) return false;
    } else if (!address.equals(other.address)) return false;
    if (email == null) {
      if (other.email != null) return false;
    } else if (!email.equals(other.email)) return false;
    if (fullName == null) {
      if (other.fullName != null) return false;
    } else if (!fullName.equals(other.fullName)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (idNumber == null) {
      if (other.idNumber != null) return false;
    } else if (!idNumber.equals(other.idNumber)) return false;
    return true;
  }
}
