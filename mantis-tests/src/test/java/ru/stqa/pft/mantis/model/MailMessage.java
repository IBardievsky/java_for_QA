package ru.stqa.pft.mantis.model;

/**
 * Created by User on 11/7/2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage (String to, String text) {
    this.to = to;
    this.text = text;
  }
}
