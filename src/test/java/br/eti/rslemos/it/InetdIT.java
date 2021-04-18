package br.eti.rslemos.it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.greaterThan;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class InetdIT {
  private static URI baseHref;

  @BeforeAll
  public static void init() throws URISyntaxException {
    String base = System.getProperty("base.href");
    baseHref = new URI(base).resolve("rest/inetd/");
  }

  @Test
  public void testDiscard() throws Exception {
    HttpGet get = new HttpGet(baseHref.resolve("discard"));

    try (
      CloseableHttpClient client = HttpClientBuilder.create().build();
      CloseableHttpResponse response = client.execute(get);
    ) {
      int statusCode = response.getStatusLine().getStatusCode();
      assertThat(statusCode, is(equalTo(HttpStatus.SC_NO_CONTENT)));
    }
  }

  @Test
  public void testEchoPath() throws Exception {
    HttpGet get = new HttpGet(baseHref.resolve("echo/").resolve("xyz"));

    try (
      CloseableHttpClient client = HttpClientBuilder.create().build();
      CloseableHttpResponse response = client.execute(get);
    ) {
      int statusCode = response.getStatusLine().getStatusCode();
      assertThat(statusCode, is(equalTo(HttpStatus.SC_OK)));

      String body = EntityUtils.toString(response.getEntity());
      assertThat(body, is(equalTo("xyz")));
    }
  }

  @Test
  public void testEchoBody() throws Exception {
    HttpPost post = new HttpPost(baseHref.resolve("echo"));
    post.setEntity(new StringEntity("xyz"));

    try (
      CloseableHttpClient client = HttpClientBuilder.create().build();
      CloseableHttpResponse response = client.execute(post);
    ) {
      int statusCode = response.getStatusLine().getStatusCode();
      assertThat(statusCode, is(equalTo(HttpStatus.SC_OK)));

      String body = EntityUtils.toString(response.getEntity());
      assertThat(body, is(equalTo("xyz")));
    }
  }

  private static final DateFormat DATETIME_FORMAT = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);

  @Test
  public void testDaytime() throws Exception {
    HttpGet get = new HttpGet(baseHref.resolve("daytime"));

    try (
      CloseableHttpClient client = HttpClientBuilder.create().build();
      CloseableHttpResponse response = client.execute(get);
    ) {
      int statusCode = response.getStatusLine().getStatusCode();
      assertThat(statusCode, is(equalTo(HttpStatus.SC_OK)));

      String body = EntityUtils.toString(response.getEntity());
      Date date = DATETIME_FORMAT.parse(body);

      final Date NOW = new Date();
      assertThat(date, is(lessThanOrEqualTo(NOW)));
      assertThat(date, is(greaterThan(new Date(NOW.getTime() - 10000L))));
    }
  }
}
