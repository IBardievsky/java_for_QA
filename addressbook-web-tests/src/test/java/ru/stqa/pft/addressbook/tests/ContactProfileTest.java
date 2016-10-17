package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 10/14/2016.
 */
public class ContactProfileTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().creationContact(new ContactData().withFirstname("Vasya").withLastname("Pupkin").withFirstAddress("Ode$$aMama").
              withHomePhone("+(1234) 56789").withMobilePhone("123456789").withWorkPhone("12345-6789").withEmail("vpupa@test.go").
              withEmail2("vpupa@test.go").withEmail3("vpupa@test.go"));
    }
  }

  @Test
  public void testContactProfile(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromProfileForm = app.contact().contactDetailsInfo(contact);

    assertThat(mergeInfo(contact), equalTo(cleaned(contactInfoFromProfileForm.getAllInfo())));
  }

  private String mergeInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAllAddress(),
            contact.getAllPhones(), contact.getAllEmails()).stream().filter((s) -> s != null && !s.equals("")).
            map(ContactProfileTest::cleaned).collect(Collectors.joining(""));
  }

  public static String cleaned (String details){
    return details.replaceAll("\\s", "")
            .replaceAll("[-()]","")
            .replaceAll("H:","")
            .replaceAll("M:","")
            .replaceAll("W:","")
            .replaceAll("\n","")
            .replaceAll("www.test.go","");
  }
}
