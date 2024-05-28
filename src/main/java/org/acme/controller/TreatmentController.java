package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.TreatmentCreationDTO;
import org.acme.service.TreatmentService;

@Path("treatment")
public class TreatmentController {

    @Inject
    TreatmentService treatmentService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTreatment(TreatmentCreationDTO treatmentCreationDTO) {
        return Response.ok(this.treatmentService.createTreatment(treatmentCreationDTO)).build();
    }

    @GET
    @Path("/culture/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTreatmentsByCulture(@PathParam("id") Integer id) {
        return Response.ok(this.treatmentService.getAllTreatmentsByCulture(id)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return treatmentService.deleteById(id);
    }
}
