package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

/**
 * Created by User on 9/26/2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().creationContact(new ContactData().withFirstname("Vasya").withLastname("Pupkin").withNickname("Vaxa")
              .withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
              .withByear("9561").withAddress("Ode$$aMama").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Eleonora").withLastname("Pupkina")
            .withNickname("Vaxa").withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("8")
            .withBmonth("July").withByear("9561").withAddress("Moskowwwwww").withGroup(null);
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size()));

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
