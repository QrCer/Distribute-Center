package useless.temporary;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by QrCeric on 16/5/5.
 */
@Path("/hi")
public class HelloSer {

    @GET
    @PostConstruct
    @Path("/hello")
    public String hello() {
        return "success";
    }
}
