package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Collaborator;
import org.acme.dto.CollaboratorDTO;
import org.acme.repository.CollaboratorRepository;
import java.util.List;


@ApplicationScoped
public class CollaboratorService {

    @Inject
    CollaboratorRepository collaboratorRepository;

    public String createCollaborator (CollaboratorDTO collaboratorDTO){
        collaboratorRepository.save(Collaborator.builder()
                .name(collaboratorDTO.getName())
                .role(collaboratorDTO.getRole())
                .build());
        return "Collaborator saved successfully.";
    }

    public CollaboratorDTO findById (Integer id){
        final Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("Collaborator not found."));
        return CollaboratorDTO.builder()
                .collaboratorID(collaborator.getCollaboratorID())
                .name(collaborator.getName())
                .role(collaborator.getRole())
                .build();
    }

    public List<CollaboratorDTO> listAllCollaborators(){
        return collaboratorRepository.findAll()
                .stream()
                .map(collaborator -> CollaboratorDTO
                        .builder()
                        .collaboratorID(collaborator.getCollaboratorID())
                        .name(collaborator.getName())
                        .role(collaborator.getRole())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        collaboratorRepository.deleteById(id);
        return ("Collaborator deleted successfully.");
    }

    public String save (){
        collaboratorRepository.save(Collaborator.builder().name("João").role("Farmer").build());
        collaboratorRepository.save(Collaborator.builder().name("Maria").role("Farmer").build());
        collaboratorRepository.save(Collaborator.builder().name("Joaquim").role("Boss").build());
        collaboratorRepository.save(Collaborator.builder().name("Hugo").role("Farmer").build());
        return "Collaborators saved successfully";
    }
}
