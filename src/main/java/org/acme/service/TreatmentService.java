package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Product;
import org.acme.domain.Treatment;
import org.acme.dto.TreatmentDTO;
import org.acme.repository.TreatmentRepository;

import java.util.List;

@ApplicationScoped
public class TreatmentService {
    @Inject
    TreatmentRepository treatmentRepository;

    public String createTreatment (TreatmentDTO treatmentDTO){
        treatmentRepository.save(Treatment.builder()
                .product(treatmentDTO.getProduct())
                .description(treatmentDTO.getDescription())
                .build());
        return "Treatment saved successfully.";
    }

    public TreatmentDTO findById (Integer id){
        final Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("Treatment not found."));
        return TreatmentDTO.builder()
                .treatmentID(treatment.getTreatmentID())
                .product(treatment.getProduct())
                .description(treatment.getDescription())
                .build();
    }

    public List<TreatmentDTO> listAllTreatments(){
        return treatmentRepository.findAll()
                .stream()
                .map(treatment -> TreatmentDTO
                        .builder()
                        .treatmentID(treatment.getTreatmentID())
                        .product(treatment.getProduct())
                        .description(treatment.getDescription())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        treatmentRepository.deleteById(id);
        return ("Treatment deleted successfully.");
    }

    public String save (){
        treatmentRepository.save(Treatment.builder().product(new Product()).description("bbb").build());
        treatmentRepository.save(Treatment.builder().product(new Product()).description("zzz").build());
        treatmentRepository.save(Treatment.builder().product(new Product()).description("sss").build());
        treatmentRepository.save(Treatment.builder().product(new Product()).description("xxx").build());
        return "Treatments saved successfully";
    }
}
