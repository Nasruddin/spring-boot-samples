package com.javatab.domain.entity;

import com.javatab.domain.base.DomainBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends DomainBase {

  private static final long serialVersionUID = 2353528370345499815L;
  private Long id;
  private String username;
  private String password;
  private String email;
  private Date lastPasswordReset;
  private String authorities;

  public User() {
    super();
  }

  public User(String username, String password, String email, Date lastPasswordReset, String authorities) {
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
    this.setLastPasswordReset(lastPasswordReset);
    this.setAuthorities(authorities);
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "username")
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password")
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "email")
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "last_password_reset")
  public Date getLastPasswordReset() {
    return this.lastPasswordReset;
  }

  public void setLastPasswordReset(Date lastPasswordReset) {
    this.lastPasswordReset = lastPasswordReset;
  }

  @Column(name = "authorities")
  public String getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }

}
