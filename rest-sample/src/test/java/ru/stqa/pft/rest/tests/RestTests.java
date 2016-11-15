package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;
import static org.testng.Assert.assertEquals;

/**
 * Created by User on 11/14/2016.
 */
public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(15);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Json test issue").withDescription("New test");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","");
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject" , newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
