package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.FieldDTO;
import org.acme.service.FieldService;
import java.util.List;

@Path("field")
public class FieldController {

    @Inject
    FieldService fieldService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FieldDTO> listAllFields(){
        return fieldService.listAllFields();
    }

    @GET
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFields(){
        return fieldService.save();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return fieldService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return fieldService.deleteById(id);
    }
}
