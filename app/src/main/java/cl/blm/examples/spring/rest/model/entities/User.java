package cl.blm.examples.spring.rest.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Persistence class for items of the table USER.
 * A 'user' is any client that connects to this app's publicly-exposed API.
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Entity
@Table(name = "USER")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u") })
public class User
    implements Serializable {
  private static final long serialVersionUID = 7091077233156297885L;

//@formatter:off
  @Id
  @Column(name = "USER_ID")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, optional = false)
  @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
  private Person person;

  @Size(min = 1, max = 100)
  @Column(name = "USER_NAME")
  private String name;

  @Size(min = 1, max = 100)
  @Column(name = "USER_PASSWORD")
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "USER_REGISTRATION_DATE")
  private Date registrationDate;

  public User() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getRegistrationDate() {
    if (registrationDate != null) {
      return (Date) registrationDate.clone();
    } else {
      return null;
    }
  }

  public void setRegistrationDate(Date registrationDate) {
    if (registrationDate != null) {
      this.registrationDate = (Date) registrationDate.clone();
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    User other = (User) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (password == null) {
      if (other.password != null) return false;
    } else if (!password.equals(other.password)) return false;
    if (person == null) {
      if (other.person != null) return false;
    } else if (!person.equals(other.person)) return false;
    if (registrationDate == null) {
      if (other.registrationDate != null) return false;
    } else if (!registrationDate.equals(other.registrationDate)) return false;
    return true;
  }
}
