package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class CreationNewContact extends TestBase {


  @Test
  public void testCeationNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Petro").withLastname("Pupkin").withNickname("Vaxa")
            .withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
            .withByear("9561").withAddress("Ode$$aMama").withGroup("test1");
    app.contact().creationContact(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt()))));
  }
}
