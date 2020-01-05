package cl.blm.examples.spring.rest.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Persistence class for items of the table PEOPLE.
 * A 'phone' is nothing more than that; a contact number that may be owned by a living individual (a 'person'). 
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Entity
@Table(name = "PHONES")
@NamedQueries({ @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p") })
public class Phone
    implements Serializable {
  private static final long serialVersionUID = 6555850390378326768L;

//@formatter:off
  @Id
  @Column(name = "PHONE_NUMBER")
  private Long number;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PERSON_ID")
  private Person person;

  public Phone() {
    super();
  }
  
  public Phone(long number) {
    this.number = number;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
