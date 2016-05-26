package useless.temporary;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/json/product")
public class JSONService {

    @GET
    @Path("/get")
    @Produces("application/json")
    public Product getProductInJSON() {

        Product product = new Product();
        product.setName("iPad 3");
        product.setQty(999);

        return product;

    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(Product product) {

        String result = "Product created : " + product;
        return Response.status(201).entity(result).build();

    }

    @GET
    @Path("/test")
    public Product test() {
        Product product = new Product();
        product.setName("iPad 33");
        product.setQty(9999);

        return product;

    }

}