package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by User on 9/26/2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().creationContact(new ContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "9", null, "9561", "Ode$$aMama", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().pressEditButton();
    ContactData contact = new ContactData (before.get(before.size() - 1).getId(), "Eleonora", "Pupkina", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "8", "July", "9561", "Moskowwwwww", null);
    app.getContactHelper().fillContactFields(contact,false);
    app.getContactHelper().submitContactUpdate();
    app.getNaviagationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
