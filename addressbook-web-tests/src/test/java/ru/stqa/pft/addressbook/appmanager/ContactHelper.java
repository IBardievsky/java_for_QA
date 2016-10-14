package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    type(By.name("mobile"),contactData.getMobilePhone());
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
    contactCache = null;
    navigateTo.homePage();
  }

  public void modify(ContactData contact) {
   pressEditButtonById(contact.getId());
   fillContactFields(contact,false);
   submitContactUpdate();
    contactCache = null;
   navigateTo.homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    submitContactDeletion();
    allertMessage();
    contactCache = null;
    navigateTo.homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count(){
    return findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    pressEditButtonById(contact.getId());
    String firstname = findElement(By.name("firstname")).getAttribute("value");
    String lastname = findElement(By.name("lastname")).getAttribute("value");
    String homePhone = findElement(By.name("home")).getAttribute("value");
    String mobilePhone = findElement(By.name("mobile")).getAttribute("value");
    String workPhone = findElement(By.name("work")).getAttribute("value");
    String email = findElement(By.name("email")).getAttribute("value");
    String email2 = findElement(By.name("email2")).getAttribute("value");
    String email3 = findElement(By.name("email3")).getAttribute("value");
    String firstAddress = findElement(By.name("address")).getAttribute("value");
    navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(contact.getFirstname()).withLastname(contact.getLastname())
            .withFirstAddress(contact.getFirstAddress()).withEmail(contact.getEmail()).withEmail2(contact.getEmail2())
            .withEmail3(contact.getEmail3()).withHomePhone(contact.getHomePhone()).withMobilePhone(contact.getMobilePhone())
            .withWorkPhone(contact.getWorkPhone());
  }

}
