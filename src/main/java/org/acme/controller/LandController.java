package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.LandCreationDTO;
import org.acme.dto.inbound.LandUpdateDTO;
import org.acme.service.LandService;

@Path("land")
public class LandController {

    @Inject
    LandService landService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLand(LandCreationDTO landCreationDTO) {
        return Response.ok(this.landService.createLand(landCreationDTO)).build();
    }

    @GET
    @Path("/company/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLandsByCompany(@PathParam("id") Integer id) {
        return Response.ok(this.landService.getAllLandsByCompany(id)).build();
    }

    @GET
    @Path("/coordinates/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLandsCoordinates(@PathParam("id") Integer id){
        return Response.ok(this.landService.getAllLandsCoordinates(id)).build();
    }

    @PUT
    @Path("/update")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLandCoordinates(LandUpdateDTO landUpdateDTO){
        return Response.ok(this.landService.updateLandCoordinates(landUpdateDTO)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return landService.deleteById(id);
    }

}
