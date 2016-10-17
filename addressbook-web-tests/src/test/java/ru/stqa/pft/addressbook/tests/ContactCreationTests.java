package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @Test
  public void testCeationNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Petro").withLastname("Pupkin").withNickname("Vaxa")
            .withCompany("CHESM").withMobilePhone("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
            .withByear("9561").withSecondAddress("Ode$$aMama").withGroup("test1");
    app.contact().creationContact(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt()))));
  }
}
