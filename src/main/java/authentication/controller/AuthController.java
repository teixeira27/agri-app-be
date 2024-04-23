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
    @Path("/login")
    public Response authenticate (@FormParam("email") String email,
                                  @FormParam("password")String password) {

        try{
            String authToken = authService.authenticate(email,password);
            return Response.ok(authToken).build();
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/register")
    public Response createAuth (@FormParam("email") String email,
                              @FormParam("password")String password) {
        try{
            String authToken = authService.createAuth(email,password);
            return Response.ok(authToken).build();
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/save")
    public Response save () {
        try{
            authService.createAuth("ines@gmail.com","ines");
            authService.createAuth("diogo@gmail.com","diogo");
            return Response.ok("SAVED!").build();
        }catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    // change password


}
