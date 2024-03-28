package authentication.controller;

import authentication.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticate (@FormParam("username") String username,
                                  @FormParam("password")String password) {

        try{
            String authToken = authService.authenticate(username,password);
            return Response.ok(authToken).build();
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/sign-in")
    public Response createAuth (@FormParam("username") String username,
                              @FormParam("password")String password) {
        try{
            String authToken = authService.createAuth(username,password);
            return Response.ok(authToken).build();
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

}
