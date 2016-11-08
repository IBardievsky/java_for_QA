package ru.stqa.pft.mantis.model;

import javax.persistence.*;


/**
 * Created by User on 11/7/2016.
 */

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Column(name = "id")
  @Id
  public int id;

  @Column(name = "username")
  public String username;

  @Column(name = "email")
  public String email;


  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
