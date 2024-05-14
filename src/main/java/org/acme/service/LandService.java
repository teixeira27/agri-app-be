package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Company;
import org.acme.domain.Land;
import org.acme.dto.inbound.LandCreationDTO;
import org.acme.dto.outbound.LandInfoDTO;
import org.acme.repository.CompanyRepository;
import org.acme.repository.LandRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LandService {

    @Inject
    CompanyRepository companyRepository;
    @Inject
    LandRepository landRepository;

    @Transactional
    public Integer createLand(LandCreationDTO landCreationDTO) {
        Optional<Company> company = this.companyRepository.findById(landCreationDTO.getCompanyId());
        if (company.isEmpty()) throw new EntityNotFoundException("Company not found!");

        Land land = Land.builder().name(landCreationDTO.getName())
                .area(landCreationDTO.getArea())
                .company(company.get())
                .build();

        this.landRepository.save(land);
        return land.getLandId();
    }

    public List<LandInfoDTO> getAllLandsByCompany(Integer companyId) {
        List<Land> lands = this.landRepository.findByCompany_CompanyId(companyId);
        List<LandInfoDTO> landInfoDTOS = new ArrayList<>();

        for (Land land : lands) {
            LandInfoDTO landInfoDTO = LandInfoDTO.builder()
                    .landId(land.getLandId())
                    .name(land.getName())
                    .build();
            landInfoDTOS.add(landInfoDTO);
        }

        return landInfoDTOS;
    }

    public String deleteById(Integer id) {
        this.landRepository.deleteById(id);
        return ("Land deleted successfully.");
    }
}
