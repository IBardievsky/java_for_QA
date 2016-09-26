package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by User on 9/22/2016.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }



  public void fillNewContactFields(NewContactData newContactData) {
    type(By.name("firstname"),newContactData.getFirstname());
    type(By.name("lastname"),newContactData.getLastname());
    type(By.name("nickname"),newContactData.getNickname());
    type(By.name("company"),newContactData.getCompany());
    type(By.name("mobile"),newContactData.getMobile());
    type(By.name("email"),newContactData.getEmail());
    select(By.name("bday"),newContactData.getBday());
    select(By.name("bmonth"),newContactData.getBmonth());
    type(By.name("byear"), newContactData.getByear());
    type(By.name("address2"),newContactData.getAddress());

  }

  public void submitContactCreation() { click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void goToAddContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.id("12"));
  }

  public void submitContactDeletion() {
    click(By.cssSelector("input[value=\"Delete\"]"));
  }

  public void allertMessage() {
    wd.switchTo().alert().accept();
  }

  public void pressEditButton() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }


  public void submitContactUpdate() {
    click(By.cssSelector("input[value=\"Update\"]"));
  }
}
