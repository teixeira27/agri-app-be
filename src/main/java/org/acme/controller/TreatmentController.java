package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.TreatmentDTO;
import org.acme.service.TreatmentService;

import java.util.List;

@Path("treatment")
public class TreatmentController {

    @Inject
    TreatmentService treatmentService;

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreatmentDTO> listAllFields(){
        return treatmentService.listAllTreatments();
    }

    @GET
    @RolesAllowed("USER")
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFields(){
        return treatmentService.save();
    }

    @GET
    @RolesAllowed("USER")
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return treatmentService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return treatmentService.deleteById(id);
    }
}
