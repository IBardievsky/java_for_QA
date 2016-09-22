package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 9/22/2016.
 */
public class NaviagationHelper extends HelperBase {
 private FirefoxDriver wd;

  public NaviagationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
