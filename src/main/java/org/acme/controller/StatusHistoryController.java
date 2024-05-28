package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.StatusHistoryCreationDTO;
import org.acme.service.StatusHistoryService;

@Path("/status")
public class StatusHistoryController {

    @Inject
    StatusHistoryService statusHistoryService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStatusHistory(StatusHistoryCreationDTO statusHistoryCreationDTO) {
        return Response.ok(this.statusHistoryService.createStatusHistory(statusHistoryCreationDTO)).build();
    }

    @DELETE
    @Path("/status/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStatusHistoryByCulture(@PathParam("id") Integer id) {
        return Response.ok(this.statusHistoryService.getAllStatusHistoryByCulture(id)).build();
    }

}
