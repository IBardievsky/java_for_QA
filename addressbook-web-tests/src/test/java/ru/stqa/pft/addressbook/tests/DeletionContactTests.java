package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by Home PC on 25.09.2016.
 */
public class DeletionContactTests extends TestBase {

  @Test
  public void testDeletionContact() {
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().creationContact(new NewContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "9", null, "9561", "Ode$$aMama", "Lesson2"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().allertMessage();

  }
}
