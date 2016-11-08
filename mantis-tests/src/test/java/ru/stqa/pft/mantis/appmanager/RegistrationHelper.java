package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Home PC on 06.11.2016.
 */
public class RegistrationHelper extends HelperBase{


  public RegistrationHelper(ApplicationManager app) {
  super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value = 'Signup']"));
  }

  public void finish(String confirmationlink, String password) {
    wd.get(confirmationlink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value = 'Update User']"));
  }

  public void logIn(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.className("button"));
  }

  public void chooseUser(String username) {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }

}
