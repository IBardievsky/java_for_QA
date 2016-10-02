package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class CreationNewContact extends TestBase {


  @Test
  public void testCeationNewContact() {
    app.getContactHelper().creationContact(new NewContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "9", null, "9561", "Ode$$aMama", "test1"));
  }
}
