package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 10/14/2016.
 */
public class ContactAddressTest extends TestBase {

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress1()).stream()
            .filter((s) -> s != null && s.equals("")).map(ContactAddressTest::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
