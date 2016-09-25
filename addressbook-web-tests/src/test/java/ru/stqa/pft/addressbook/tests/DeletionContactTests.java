package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Home PC on 25.09.2016.
 */
public class DeletionContactTests extends TestBase {

  @Test
  public void testDeletionContact() {
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().allertMessage();

  }
}
