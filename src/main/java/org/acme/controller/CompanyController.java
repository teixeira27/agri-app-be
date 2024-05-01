package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.CompanyCreationDTO;
import org.acme.dto.inbound.CompanyJoinDTO;
import org.acme.service.CompanyService;


@Path("company")
public class CompanyController {
    @Inject
    CompanyService companyService;

    @POST
    @Path("/create")
    @RolesAllowed("USER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompany(CompanyCreationDTO companyInboundDTO) {
        return Response.ok(this.companyService.createCompany(companyInboundDTO)).build();
    }

    @POST
    @Path("/join")
    @RolesAllowed("USER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response joinCompany(CompanyJoinDTO companyJoinDTO) {
        return Response.ok(this.companyService.joinCompany(companyJoinDTO)).build();
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return companyService.deleteById(id);
    }

}
