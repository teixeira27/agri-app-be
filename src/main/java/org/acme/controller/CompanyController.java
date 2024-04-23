package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.CompanyDTO;
import org.acme.service.CompanyService;
import java.util.List;

@Path("company")
public class CompanyController {
    @Inject
    CompanyService companyService;

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyDTO> listAllCompanies(){
        return companyService.listAllCompanies();
    }

    @GET
    @Path("/save")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveCompanies(){
        return companyService.save();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return companyService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return companyService.deleteById(id);
    }

    //create company, join company
}
