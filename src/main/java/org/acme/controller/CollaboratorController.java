package org.acme.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.CollaboratorDTO;
import org.acme.service.CollaboratorService;
import java.util.List;

@Path("collaborator")
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CollaboratorDTO> listAllCollaborators(){
        return collaboratorService.listAllCollaborators();
    }

    @GET
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveCollaborators(){
        return collaboratorService.save();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return collaboratorService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return collaboratorService.deleteById(id);
    }
}
