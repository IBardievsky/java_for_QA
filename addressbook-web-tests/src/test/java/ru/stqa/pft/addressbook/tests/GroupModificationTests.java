package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Home PC on 25.09.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification (){
    app.getNaviagationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creationGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Lesson2", "Video12", "AutomationFuckuper"));
    app.getGroupHelper().submitGroupModification();
  }
}
