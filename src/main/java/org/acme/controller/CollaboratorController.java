package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.CollaboratorCreationDTO;
import org.acme.dto.inbound.CollaboratorLoginDTO;
import org.acme.dto.outbound.LoginInfoDTO;
import org.acme.service.CollaboratorService;

@Path("collaborator")
public class CollaboratorController {

    @Inject
    CollaboratorService collaboratorService;

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAuth(CollaboratorCreationDTO requestDTO) {
        try {
            String authToken = collaboratorService.createCollaborator(requestDTO);
            return Response.ok(authToken).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(CollaboratorLoginDTO requestDTO) {

        try {
            LoginInfoDTO loginInfoDTO = collaboratorService.verifyCollaborator(requestDTO);
            return Response.ok(loginInfoDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return collaboratorService.deleteById(id);
    }
}
