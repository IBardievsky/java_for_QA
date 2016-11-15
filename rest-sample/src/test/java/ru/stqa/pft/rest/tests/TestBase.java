package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;

/**
 * Created by User on 9/22/2016.
 */
public class TestBase {

  boolean isIssueOpen(int issueId) throws IOException {
    String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","").execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement issue = issues.getAsJsonArray().get(0);
    JsonElement state = issue.getAsJsonObject().get("state_name");
    String stateName = state.toString();
    if (!stateName.equals("\"Closed\"") && !stateName.equals("\"Resolved\"")){
      return true;
    } else {
      return false;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
