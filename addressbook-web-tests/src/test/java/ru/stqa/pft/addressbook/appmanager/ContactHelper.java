package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by User on 9/22/2016.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }



  public void fillNewContactFields(NewContactData newContactData) {
    type(By.name("firstname"),newContactData.getFirstname());
    type(By.name("lastname"),newContactData.getLastname());
    type(By.name("nickname"),newContactData.getNickname());
    type(By.name("company"),newContactData.getCompany());
    type(By.name("mobile"),newContactData.getMobile());
    type(By.name("email"),newContactData.getEmail());
  }

  public void submitContactCreation() { click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void goToAddContactPage() {
    click(By.linkText("add new"));
  }
}
