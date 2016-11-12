package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import static ru.stqa.pft.mantis.tests.TestBase.app;

/**
 * Created by Home PC on 12.11.2016.
 */
public class SoapTests {

  @Test
  public void testGenProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-1.3.3/api/soap/mantisconnect.php"));
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    System.out.println(projects.length);
    for (ProjectData project: projects) {
      System.out.println(project.getName());
    }
  }
}
