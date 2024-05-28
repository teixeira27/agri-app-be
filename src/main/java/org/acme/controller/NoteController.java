package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.NoteCreationDTO;
import org.acme.service.NoteService;

@Path("note")
public class NoteController {

    @Inject
    NoteService noteService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNote(NoteCreationDTO noteCreationDTO){
        return Response.ok(this.noteService.createNote(noteCreationDTO)).build();
    }

    @GET
    @Path("/company/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotesByCompanyId(@PathParam("id") Integer id){
        return Response.ok(this.noteService.getAllNotesByCompany(id)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return noteService.deleteById(id);
    }
}
