package org.acme.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.inbound.*;
import org.acme.dto.outbound.CollaboratorInfoDTO;
import org.acme.dto.outbound.LoginInfoDTO;
import org.acme.service.CollaboratorService;

@Path("collaborator")
public class CollaboratorController {

    @Inject
    CollaboratorService collaboratorService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Response createAuth(CollaboratorCreationDTO requestDTO) {
        try {
            CollaboratorInfoDTO result = collaboratorService.createCollaborator(requestDTO);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyEmail(EmailVerificationDTO requestDTO){
        try {
            return Response.ok(collaboratorService.verifyEmail(requestDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(CollaboratorLoginDTO requestDTO) {
        try {
            LoginInfoDTO loginInfoDTO = collaboratorService.verifyCollaborator(requestDTO);
            return Response.ok(loginInfoDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/resetPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(ResetPasswordDTO requestDTO) {
        try {
            return Response.ok(collaboratorService.resetPassword(requestDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/newPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newPassword(NewPasswordDTO requestDTO) {
        try {
            return Response.ok(collaboratorService.newPassword(requestDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") Integer id) {
        return collaboratorService.deleteById(id);
    }
}
