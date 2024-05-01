package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.domain.Collaborator;
import org.acme.dto.inbound.CollaboratorCreationDTO;
import org.acme.service.CollaboratorService;

@Path("collaborator")
public class CollaboratorController {

    @Inject
    CollaboratorService collaboratorService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCollaborator(CollaboratorCreationDTO collaboratorCreationDTO) {
        return Response.ok(collaboratorService.createCollaborator(collaboratorCreationDTO)).build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Collaborator listById(@PathParam("id") Integer id) {     // create a converter to send a DTO
        return collaboratorService.findById(id);
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return collaboratorService.deleteById(id);
    }
}
