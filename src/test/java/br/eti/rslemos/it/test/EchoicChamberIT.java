package br.eti.rslemos.it.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EchoicChamberIT {
  private static URI baseHref;

  @BeforeClass
  public static void init() throws URISyntaxException {
    String base = System.getProperty("base.href");
    baseHref = new URI(base).resolve("rest/inetd/");
  }

  @Deployment(testable = true)
  public static WebArchive createDeployment() {
    String warName = System.getProperty("arquillian.war.name");
    WebArchive archive = ShrinkWrap.create(WebArchive.class, warName)
      .addPackages(true, "br.eti.rslemos.it.test");
    return archive;
  }

  @Test
  @RunAsClient
  public void testEchoPath() throws Exception {
    HttpGet get = new HttpGet(baseHref.resolve("echo/").resolve("xyz"));

    try (CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(get);) {
      int statusCode = response.getStatusLine().getStatusCode();
      assertThat(statusCode, is(equalTo(HttpStatus.SC_OK)));

      String body = EntityUtils.toString(response.getEntity());
      assertThat(body, is(equalTo("xyz")));
    }
  }

}
