package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.domain.Company;
import org.acme.dto.inbound.CompanyCreationDTO;
import org.acme.dto.inbound.CompanyJoinDTO;
import org.acme.repository.CompanyRepository;

import java.util.List;

@ApplicationScoped
public class CompanyService {

    @Inject
    CollaboratorService collaboratorService;

    @Inject
    CompanyRepository companyRepository;

    @Transactional
    public Integer createCompany(CompanyCreationDTO companyCreationDTO) {
        Collaborator collaborator = this.collaboratorService.findById(companyCreationDTO.getCollaboratorId());

        if (collaborator == null) throw new EntityNotFoundException("Collaborator not found!");

        Company company = Company.builder().name(companyCreationDTO.getName())
                .vat(companyCreationDTO.getVat())
                .pin(companyCreationDTO.getPin())
                .collaborators(List.of(collaborator))
                .build();

        collaborator.setCompany(company);
        this.companyRepository.save(company);
        return company.getCompanyId();
    }

    @Transactional
    public Integer joinCompany(CompanyJoinDTO companyJoinDTO) {
        Collaborator collaborator = this.collaboratorService.findById(companyJoinDTO.getCollaboratorId());

        if (collaborator == null) throw new EntityNotFoundException("Collaborator not found!");
        if (collaborator.getCompany() != null)
            throw new IllegalArgumentException("Collaborator already has a Company!");

        Company company = this.companyRepository.findByVat(companyJoinDTO.getVat());

        if (companyJoinDTO.getPin() == company.getPin()) {
            collaborator.setCompany(company);
            company.getCollaborators().add(collaborator);
            return company.getCompanyId();
        } else throw new IllegalArgumentException("Wrong PIN");
    }

    @Transactional
    public String deleteById(Integer id) {
        this.companyRepository.deleteById(id);
        return "Company deleted successfully.";
    }
}
