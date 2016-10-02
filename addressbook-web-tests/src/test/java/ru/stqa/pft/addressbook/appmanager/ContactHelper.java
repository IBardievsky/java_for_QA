package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by User on 9/22/2016.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  NaviagationHelper nhelper = new NaviagationHelper(wd);

  public void fillContactFields(NewContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());
    select(By.name("bday"),contactData.getBday());
    select(By.name("bmonth"),contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"),contactData.getAddress());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() { click(By.cssSelector("input[value=\"Enter\"]"));
  }

  public void goToAddContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
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



  public void creationContact(NewContactData contact) {
    goToAddContactPage();
    fillContactFields(contact,true);
    submitContactCreation();
    nhelper.gotoHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
