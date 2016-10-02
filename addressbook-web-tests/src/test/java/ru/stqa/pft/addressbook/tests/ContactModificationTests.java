package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by User on 9/26/2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().creationContact(new NewContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "9", null, "9561", "Ode$$aMama", "Lesson2"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().pressEditButton();
    app.getContactHelper().fillContactFields(new NewContactData("Vasya", "Pupkin", "Vaxa", "CHESM", "123456789", "vpupa@test.go", "8", "July", "9561", "Moskowwwwww", null),false);
    app.getContactHelper().submitContactUpdate();
  }
}
