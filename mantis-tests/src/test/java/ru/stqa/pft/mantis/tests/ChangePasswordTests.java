package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by User on 11/7/2016.
 */
public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    String adminlogin = "administrator";
    String adminpass = "pass";
    String newpass = "newPassword";

    UserData user = app.db().user();
    String username = user.getUsername();
    String email = user.getEmail();

    app.registration().logIn(adminlogin, adminpass);
    app.registration().chooseUser(username);


    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationlink = findConfirmationlink(mailMessages, email);

    app.registration().finish(confirmationlink, newpass);
    assertTrue(app.newSession().login(username, newpass));
  }

  private String findConfirmationlink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}


