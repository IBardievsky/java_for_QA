package ru.stqa.pft.addressbook.model;

public class NewContactData {
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String mobile;
  private final String email;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String address;




  public NewContactData(String firstname, String lastname, String nickname, String company, String mobile, String email, String bday, String bmonth, String byear, String address) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.mobile = mobile;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.address = address;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getBday(){ return bday; }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getAddress() {
    return address;
  }
}
