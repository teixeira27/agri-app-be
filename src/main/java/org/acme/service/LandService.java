package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Company;
import org.acme.domain.Land;
import org.acme.dto.inbound.LandCreationDTO;
import org.acme.dto.inbound.LandUpdateDTO;
import org.acme.dto.outbound.LandCoordinatesDTO;
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
                .latitude(landCreationDTO.getLatitude())
                .longitude(landCreationDTO.getLongitude())
                .location(landCreationDTO.getLocation())
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

    public List<LandCoordinatesDTO> getAllLandsCoordinates(Integer companyId) {
        List<Land> lands = this.landRepository.findByCompany_CompanyId(companyId);
        List<LandCoordinatesDTO> landCoordinatesDTOS = new ArrayList<>();

        for (Land land: lands){
            if (land.getLatitude() != null && land.getLongitude() != null)
            {
                LandCoordinatesDTO landCoordinatesDTO = LandCoordinatesDTO.builder()
                        .landId(land.getLandId())
                        .latitude(land.getLatitude())
                        .longitude(land.getLongitude())
                        .build();
                landCoordinatesDTOS.add(landCoordinatesDTO);
            }
        }

        return landCoordinatesDTOS;
    }

    @Transactional
    public LandInfoDTO updateLandCoordinates(LandUpdateDTO landUpdateDTO){
        Optional<Land> optionalLand = this.landRepository.findById(landUpdateDTO.getLandId());
        if (optionalLand.isEmpty()) throw new EntityNotFoundException("Land not found!");

        Land land = optionalLand.get();
        land.setLatitude(landUpdateDTO.getLatitude());
        land.setLongitude(landUpdateDTO.getLongitude());
        this.landRepository.save(land);

        return LandInfoDTO.builder().landId(land.getLandId()).name(land.getName()).build();
    }

    @Transactional
    public String deleteById(Integer id) {
        this.landRepository.deleteById(id);
        return ("Land deleted successfully.");
    }
}
