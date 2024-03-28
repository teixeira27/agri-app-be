package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.ProductDTO;
import org.acme.service.ProductService;

import java.util.List;

public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> listAllFields(){
        return productService.listAllFields();
    }

    @GET
    @Path("/save")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFields(){
        return productService.save();
    }

    @GET
    @Path("{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String listById (@PathParam("id") Integer id){
        return productService.findById(id).toString();
    }

    @GET
    @Path("delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id){
        return productService.deleteById(id);
    }
}
