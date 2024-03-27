package authentication.controller;

import authentication.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("auth")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String authenticate (@FormParam("username") String username,
                                @FormParam("password")String password) throws Exception {

        return authService.authenticate(username,password);
    }
}
