package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreationNewContact extends TestBase {


  @Test
  public void testCeationNewContact() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Petro").withLastname("Pupkin").withNickname("Vaxa")
            .withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
            .withByear("9561").withAddress("Ode$$aMama").withGroup("test1");
    app.contact().creationContact(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() + 1);

    contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
