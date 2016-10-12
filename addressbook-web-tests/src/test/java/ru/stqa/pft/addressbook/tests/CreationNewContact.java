package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class CreationNewContact extends TestBase {


  @Test
  public void testCeationNewContact() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Petro").withLastname("Pupkin").withNickname("Vaxa")
            .withCompany("CHESM").withMobile("123456789").withEmail("vpupa@test.go").withBday("9").withBmonth(null)
            .withByear("9561").withAddress("Ode$$aMama").withGroup("test1");
    app.contact().creationContact(contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
