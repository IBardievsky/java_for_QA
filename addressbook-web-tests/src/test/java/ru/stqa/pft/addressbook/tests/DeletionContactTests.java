package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Home PC on 25.09.2016.
 */
public class DeletionContactTests extends TestBase {

  @Test
  public void testDeletionContact() {
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().creationContact(new ContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "9", null, "9561", "Ode$$aMama", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().allertMessage();
    app.getNaviagationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
