package br.eti.rslemos.it;

import java.io.Reader;
import javax.inject.Inject;
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
  @Inject BlackHole hole;
  @Inject EchoicChamber chamber;
  @Inject SwissClock clock;

  @Context
  public void setUriInfo(UriInfo info) {
    System.out.printf("request URI: %s\n", info.getRequestUri());
    System.out.printf("matched URIs: %s\n", info.getMatchedURIs());
    System.out.printf("matched resources: %s\n", info.getMatchedResources());
  }

  @GET @POST @PUT @DELETE @HEAD @OPTIONS @PATCH
  @Path("/discard")
  public void discard() {
    hole.feed();
  }

  @GET
  @Path("/echo/{argument}")
  public String echo(@PathParam("argument") String argument) {
    return chamber.introduce(argument);
  }

  @POST @PUT
  @Path("/echo")
  public Reader echo(Reader argument) {
    return chamber.introduce(argument);
  }

  @GET
  @Path("/daytime")
  public String daytime() {
    return clock.read();
  }
}
