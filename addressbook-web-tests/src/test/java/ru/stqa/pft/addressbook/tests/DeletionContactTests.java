package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Home PC on 25.09.2016.
 */
public class DeletionContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().creationContact(new ContactData().withFirstname("Vasya").withLastname("Pupkin").withNickname("Vaxa")
              .withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
              .withByear("9561").withAddress("Ode$$aMama").withGroup("test1"));
    }
  }

  @Test
  public void testDeletionContact() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
