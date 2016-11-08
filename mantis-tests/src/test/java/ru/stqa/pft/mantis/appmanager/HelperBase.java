package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

/**
 * Created by User on 9/22/2016.
 */
public class HelperBase {
  protected final ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null){
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null){
      findElement(locator).sendKeys(file.getAbsolutePath());
      }
    }

  protected String getText(By locator){
    return wd.findElement(locator).getText();

  }

  protected List<WebElement> findElements(By locator) {
    return wd.findElements(locator);
  }

  protected WebElement findElement(By locator) {
    return wd.findElement(locator);
  }

  protected WebDriver.TargetLocator switchTo() {
    return wd.switchTo();
  }

  protected WebDriver.Navigation navigate(){
    return wd.navigate();
  }

  protected void select(By locator, String text){
    if (text != null){
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)){
        new Select(wd.findElement(locator)).selectByVisibleText(text);
      }
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }
}
