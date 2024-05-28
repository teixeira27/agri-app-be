package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.CultureCreationDTO;
import org.acme.service.CultureService;

@Path("/culture")
public class CultureController {

    @Inject
    CultureService cultureService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCulture(CultureCreationDTO cultureCreationDTO) {
        return Response.ok(this.cultureService.createCulture(cultureCreationDTO)).build();
    }

    @GET
    @Path("/land/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCulturesByLand(@PathParam("id") Integer id) {
        return Response.ok(this.cultureService.getAllCulturesByLand(id)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return cultureService.deleteById(id);
    }

}
