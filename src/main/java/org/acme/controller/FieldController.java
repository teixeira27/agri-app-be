package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.FieldDTO;
import org.acme.service.LandService;
import java.util.List;

@Path("field")
public class FieldController {

    @Inject
    LandService landService;

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FieldDTO> listAllFields(){
        return landService.listAllFields();
    }

    @GET
    @Path("/save")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFields(){
        return landService.save();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return landService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return landService.deleteById(id);
    }

    //show all lands by user

    //create cultures
    //show all cultures by land

    //how to store photos

}
