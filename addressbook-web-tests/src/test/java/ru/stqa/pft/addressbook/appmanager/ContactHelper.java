package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/22/2016.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  NaviagationHelper navhelp = new NaviagationHelper(wd);

  public void fillContactFields(ContactData contactData, boolean creation) {
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
      select(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() { click(By.cssSelector("input[value=\"Enter\"]"));
  }

  public void goToAddContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    }

  public void submitContactDeletion() {
    click(By.cssSelector("input[value=\"Delete\"]"));
  }

  public void allertMessage() {
    wd.switchTo().alert().accept();
  }

  public void pressEditButton(int index) {
    wd.findElements (By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
  }


  public void submitContactUpdate() {
    click(By.cssSelector("input[value=\"Update\"]"));
  }



  public void creationContact(ContactData contact) {
    goToAddContactPage();
    fillContactFields(contact,true);
    submitContactCreation();
    navhelp.gotoHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.cssSelector("tr>td:nth-of-type(3)")).getText();
      String lastName = element.findElement(By.cssSelector("tr>td:nth-of-type(2)")).getText();
      String eMail = element.findElement(By.cssSelector("tr>td:nth-of-type(5)")).getText();
      String mobilePhone = element.findElement(By.cssSelector("tr>td:nth-of-type(6)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, mobilePhone, eMail, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
