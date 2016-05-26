package useless.temporary;

import javax.jws.WebParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public interface HelloWorld {

    @GET
    @Path("/sayHi")
    @Produces("application/json")
    String sayHi(@WebParam(name = "who") String who);

    String sayHello(@WebParam(name = "who") String who);

}  