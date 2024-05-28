package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Culture;
import org.acme.domain.Land;
import org.acme.dto.inbound.CultureCreationDTO;
import org.acme.dto.outbound.CultureInfoDTO;
import org.acme.repository.CultureRepository;
import org.acme.repository.LandRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CultureService {

    @Inject
    LandRepository landRepository;
    @Inject
    CultureRepository cultureRepository;

    @Transactional
    public Integer createCulture(CultureCreationDTO cultureCreationDTO) {
        Optional<Land> land = this.landRepository.findById(cultureCreationDTO.getLandId());
        if (land.isEmpty()) throw new EntityNotFoundException("Land not found!");

        Culture culture = Culture.builder().name(cultureCreationDTO.getName())
                .land(land.get())
                .build();
        this.cultureRepository.save(culture);
        return culture.getCultureId();
    }

    public List<CultureInfoDTO> getAllCulturesByLand(Integer landId) {
        List<Culture> cultures = this.cultureRepository.findByLand_LandId(landId);
        List<CultureInfoDTO> cultureInfoDTOS = new ArrayList<>();

        for (Culture culture : cultures) {
            CultureInfoDTO cultureInfoDTO = CultureInfoDTO.builder()
                    .cultureId(culture.getCultureId())
                    .name(culture.getName())
                    .build();
            cultureInfoDTOS.add(cultureInfoDTO);
        }
        return cultureInfoDTOS;
    }

    @Transactional
    public String deleteById(Integer id) {
        this.cultureRepository.deleteById(id);
        return ("Culture deleted successfully.");
    }
}
