package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 9/22/2016.
 */
public class ContactHelper extends HelperBase{

  NaviagationHelper navigateTo;

  public ContactHelper(WebDriver wd) {
    super(wd);
    navigateTo = new NaviagationHelper(wd);
  }

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

  public void submitContactCreation() {
    click(By.cssSelector("input[value=\"Enter\"]"));
  }

  public void goToAddContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

  public void submitContactDeletion() {
    click(By.cssSelector("input[value=\"Delete\"]"));
  }

  public void allertMessage() {
    switchTo().alert().accept();
  }

  public void pressEditButtonById(int id) {
    findElement(By.cssSelector("a[href=\"edit.php?id="+ id +"\"]")).click();
  }

  public void submitContactUpdate() {
    click(By.cssSelector("input[value=\"Update\"]"));
  }

  public void creationContact(ContactData contact) {
    goToAddContactPage();
    fillContactFields(contact,true);
    submitContactCreation();
    navigateTo.homePage();
  }

  public void modify(ContactData contact) {
   pressEditButtonById(contact.getId());
   fillContactFields(contact,false);
   submitContactUpdate();
   navigateTo.homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    submitContactDeletion();
    allertMessage();
    navigateTo.homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row : rows) {
      String firstName = row.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
      String lastName = row.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
      String eMail = row.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
      String mobilePhone = row.findElement(By.cssSelector("td:nth-of-type(6)")).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withMobile(mobilePhone).withEmail(eMail));
    }
    return contacts;
  }
}
