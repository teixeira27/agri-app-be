package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.dto.inbound.CollaboratorCreationDTO;
import org.acme.repository.CollaboratorRepository;


@ApplicationScoped
public class CollaboratorService {

    @Inject
    CollaboratorRepository collaboratorRepository;

    @Transactional
    public Integer createCollaborator(CollaboratorCreationDTO collaboratorCreationDTO) {
        Collaborator collaborator = Collaborator.builder()
                .name(collaboratorCreationDTO.getName()).build();
        this.collaboratorRepository.save(collaborator);
        return collaborator.getCollaboratorId();
    }

    // update collaborator

    public Collaborator findById(Integer id) {
        final Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found."));
        return collaborator;
    }

    public String deleteById(Integer id) {
        collaboratorRepository.deleteById(id);
        return ("Collaborator deleted successfully.");
    }
}
