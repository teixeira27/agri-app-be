package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Collaborator;
import org.acme.domain.Company;
import org.acme.dto.inbound.CompanyCreationDTO;
import org.acme.dto.inbound.CompanyJoinDTO;
import org.acme.dto.outbound.CompanyInfoDTO;
import org.acme.repository.CollaboratorRepository;
import org.acme.repository.CompanyRepository;

import java.util.List;

@ApplicationScoped
public class CompanyService {

    @Inject
    CollaboratorService collaboratorService;

    @Inject
    CompanyRepository companyRepository;
    @Inject
    CollaboratorRepository collaboratorRepository;

    @Transactional
    public CompanyInfoDTO createCompany(CompanyCreationDTO companyCreationDTO) {
        long vat = companyCreationDTO.getVat();
        int length = (int) (Math.log10(vat) + 1);
        if (length != 9) throw new IllegalArgumentException("Vat must have 9 digits!");

        int pin = companyCreationDTO.getPin();
        length = (int)   (Math.log10(pin) + 1);
        if (length < 4)  throw new IllegalArgumentException("Pin must have at least 4 digits!");

        Collaborator collaborator = this.collaboratorService.findById(companyCreationDTO.getCollaboratorId());
        if (collaborator == null) throw new EntityNotFoundException("Collaborator not found!");

        Company company = Company.builder().name(companyCreationDTO.getName())
                .vat(companyCreationDTO.getVat())
                .pin(companyCreationDTO.getPin())
                .collaborators(List.of(collaborator))
                .build();

        collaborator.setCompany(company);
        this.companyRepository.save(company);

        return CompanyInfoDTO.builder()
                .name(company.getName())
                .vat(company.getVat())
                .companyId(company.getCompanyId())
                .build();
    }

    @Transactional
    public CompanyInfoDTO joinCompany(CompanyJoinDTO companyJoinDTO) {
        Collaborator collaborator = this.collaboratorService.findById(companyJoinDTO.getCollaboratorId());

        if (collaborator == null) throw new EntityNotFoundException("Collaborator not found!");
        if (collaborator.getCompany() != null)
            throw new IllegalArgumentException("Collaborator already has a Company!");

        Company company = this.companyRepository.findByVat(companyJoinDTO.getVat());

        if (companyJoinDTO.getPin() == company.getPin()) {
            collaborator.setCompany(company);
            company.getCollaborators().add(collaborator);

            return CompanyInfoDTO.builder()
                    .name(company.getName())
                    .vat(company.getVat())
                    .companyId(company.getCompanyId())
                    .build();
        } else throw new IllegalArgumentException("Wrong PIN");
    }

    @Transactional
    public String deleteById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        List<Collaborator> collaborators = company.getCollaborators();
        for (Collaborator collaborator : collaborators) {
            collaborator.setCompany(null);
            this.collaboratorRepository.save(collaborator);
        }
        this.companyRepository.deleteById(id);
        return "Company deleted successfully.";
    }
}
