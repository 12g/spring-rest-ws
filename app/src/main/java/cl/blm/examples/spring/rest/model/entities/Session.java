package cl.blm.examples.spring.rest.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistence class for items of the table SESSION.
 * A 'session' is an API access declaration. It allows the Auth system to identify who and when is any user identify.
 *
 * @author Benjamin Guillermo <got12g at gmail.com>
 */
@Entity
@Table(name = "SESSIONS")
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT u FROM Session u") })
public class Session
    implements Serializable {
  private static final long serialVersionUID = -2380350130951103466L;

  //@formatter:off
  @Id
  @Column(name = "SESSION_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, optional = false)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = true, updatable = true)
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SESSION_OPEN_DATE")
  private Date openDate;

  @Column(name = "SESSION_HASH")
  private String hash;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SESSION_CLOSE_DATE")
  private Date closeDate;

  public Session() {
    super();
  }

  public Session(Long id) {
    super();
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getOpenDate() {
    if (openDate != null) {
      return (Date)openDate.clone();
    } else {
      return null;
    }
  }

  public void setOpenDate(Date openDate) {
    if (openDate != null) {
      this.openDate = (Date)openDate.clone();
    }
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public Date getCloseDate() {
    if (closeDate != null) {
      return (Date)closeDate.clone();
    } else {
      return null;
    }
  }

  public void setCloseDate(Date closeDate) {
    if (closeDate != null) {
      this.closeDate = (Date)closeDate.clone();
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Session other = (Session) obj;
    if (hash == null) {
      if (other.hash != null) return false;
    } else if (!hash.equals(other.hash)) return false;
    return true;
  }
}
