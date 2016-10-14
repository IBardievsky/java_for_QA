package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Home PC on 25.09.2016.
 */
public class DeletionContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().creationContact(new ContactData().withFirstname("Vasya").withLastname("Pupkin").withNickname("Vaxa")
              .withCompany("CHESM").withMobilePhone("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
              .withByear("9561").withAddress("Ode$$aMama").withGroup("test1"));
    }
  }

  @Test
  public void testDeletionContact() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
