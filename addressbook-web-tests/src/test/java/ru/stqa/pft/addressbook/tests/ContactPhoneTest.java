package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 10/14/2016.
 */
public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    MatcherAssert.assertThat(contact.getHomePhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getHomePhone()));
    MatcherAssert.assertThat(contact.getMobilePhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getMobilePhone()));
    MatcherAssert.assertThat(contact.getWorkPhone(), CoreMatchers.equalTo(contactInfoFromEditForm.getWorkPhone()));

  }
}
