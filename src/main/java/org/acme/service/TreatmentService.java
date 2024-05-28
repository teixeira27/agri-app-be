package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.acme.domain.Culture;
import org.acme.domain.Treatment;
import org.acme.dto.inbound.TreatmentCreationDTO;
import org.acme.dto.outbound.TreatmentInfoDTO;
import org.acme.repository.CultureRepository;
import org.acme.repository.TreatmentRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TreatmentService {
    @Inject
    TreatmentRepository treatmentRepository;
    @Inject
    CultureRepository cultureRepository;

    @Transactional
    public Integer createTreatment(TreatmentCreationDTO treatmentCreationDTO) {
        Optional<Culture> culture = this.cultureRepository.findById(treatmentCreationDTO.getCultureId());
        if (culture.isEmpty()) throw new EntityNotFoundException("Culture not found!");

        Treatment treatment = Treatment.builder()
                .productName(treatmentCreationDTO.getProductName())
                .description(treatmentCreationDTO.getDescription())
                .date(Date.from(Instant.now()))
                .securityDays(treatmentCreationDTO.getSecurityDays())
                .culture(culture.get())
                .build();
        this.treatmentRepository.save(treatment);
        return treatment.getTreatmentId();
    }

    public List<TreatmentInfoDTO> getAllTreatmentsByCulture(Integer cultureId) {
        List<Treatment> treatments = this.treatmentRepository.findByCulture_CultureId(cultureId);
        List<TreatmentInfoDTO> treatmentInfoDTOS = new ArrayList<>();

        for (Treatment treatment : treatments) {
            TreatmentInfoDTO treatmentInfoDTO = TreatmentInfoDTO.builder()
                    .treatmentId(treatment.getTreatmentId())
                    .productName(treatment.getProductName())
                    .date(treatment.getDate().toString())
                    .description(treatment.getDescription())
                    .securityDays(treatment.getSecurityDays())
                    .build();
            treatmentInfoDTOS.add(treatmentInfoDTO);
        }
        return treatmentInfoDTOS;
    }

    @Transactional
    public String deleteById(Integer id) {
        treatmentRepository.deleteById(id);
        return ("Treatment deleted successfully.");
    }
}
