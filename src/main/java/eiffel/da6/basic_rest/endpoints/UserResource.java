package eiffel.da6.basic_rest.endpoints;

import eiffel.da6.basic_rest.dao.UserDAO;
import eiffel.da6.basic_rest.dto.UserDTO;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;



/**
 * Root resource (exposed at "users" path)
 */
@Path("users")
public class UserResource {

    @Context
    UriInfo uri;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        return new UserDAO().getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(JsonObject body) {
        String username = body.getString("username");
        String password = body.getString("password");
        String date = body.getString("date_naiss");
        int response = new UserDAO().createUser(username, password, date);
        if (response != -1) {
            return Response.created(URI.create(uri.getAbsolutePath().toString() + response)).build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserById(@PathParam("id") int id) {
        return  new UserDAO().getUserById(id).toString();
    }

    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_HTML)
    public String getUserByName(@QueryParam("value") String name){
        return new UserDAO().getUserByName(name).toHTML();
    }
}
