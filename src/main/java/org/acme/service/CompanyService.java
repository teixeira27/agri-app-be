package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Company;
import org.acme.dto.CompanyDTO;
import org.acme.repository.CompanyRepository;
import java.util.List;

@ApplicationScoped
public class CompanyService {

    @Inject
    CompanyRepository companyRepository;

    public String createCompany (CompanyDTO companyDTO){
        companyRepository.save(Company.builder()
                .name(companyDTO.getName())
                .VAT(companyDTO.getVAT())
                .address(companyDTO.getAddress())
                .build());
        return "Company created successfully.";
    }
    public CompanyDTO findById (Integer id){
        final Company company = companyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException ("Company not found."));
        return CompanyDTO.builder()
                .companyID(company.getCompanyID())
                .name(company.getName())
                .VAT(company.getVAT())
                .address(company.getAddress())
                .build();
    }

    public List<CompanyDTO> listAllCompanies (){
        return companyRepository.findAll()
                .stream()
                .map(company -> CompanyDTO
                        .builder()
                        .companyID(company.getCompanyID())
                        .name(company.getName())
                        .VAT(company.getVAT())
                        .address(company.getAddress())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        companyRepository.deleteById(id);
        return "Company deleted successfully.";
    }

    public String save (){
        companyRepository.save(Company.builder().name("Efacec").VAT(123).address("bbb").build());
        companyRepository.save(Company.builder().name("NOS").VAT(312).address("aaa").build());
        companyRepository.save(Company.builder().name("GALP").VAT(321).address("sss").build());
        companyRepository.save(Company.builder().name("EDP").VAT(213).address("zzz").build());
        return "Companies saved successfully";
    }
}
