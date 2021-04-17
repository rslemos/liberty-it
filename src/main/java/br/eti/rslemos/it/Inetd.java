package br.eti.rslemos.it;

import java.io.Reader;
import java.text.DateFormat;
import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/inetd")
public class Inetd {
  @Context
  public void setUriInfo(UriInfo info) {
    System.out.printf("request URI: %s\n", info.getRequestUri());
    System.out.printf("matched URIs: %s\n", info.getMatchedURIs());
    System.out.printf("matched resources: %s\n", info.getMatchedResources());
  }

  @GET @POST @PUT @DELETE @HEAD @OPTIONS @PATCH
  @Path("/discard")
  public void discard() {
    System.out.println("discarding");
  }

  @GET
  @Path("/echo/{argument}")
  public String echo(@PathParam("argument") String argument) {
    return argument;
  }

  @POST @PUT
  @Path("/echo")
  public Reader echo(Reader argument) {
    return argument;
  }

  private static final DateFormat DATETIME_FORMAT = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
  @GET
  @Path("/daytime")
  public String daytime() {
    final Date NOW = new Date();
    return DATETIME_FORMAT.format(NOW);
  }
}
