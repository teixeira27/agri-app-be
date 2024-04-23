package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Land;
import org.acme.dto.FieldDTO;
import org.acme.repository.LandRepository;
import java.util.List;

@ApplicationScoped
public class LandService {

    @Inject
    LandRepository landRepository;

    public String createField (FieldDTO fieldDTO){
        landRepository.save(Land.builder()
                .name(fieldDTO.getName())
                .area(fieldDTO.getArea())
                .coordinates(fieldDTO.getCoordinates())
                .localization(fieldDTO.getLocalization())
                .build());
        return "Land saved successfully.";
    }

    public FieldDTO findById (Integer id){
        final Land land = landRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("Land not found."));
        return FieldDTO.builder()
                .fieldID(land.getFieldID())
                .name(land.getName())
                .area(land.getArea())
                .coordinates(land.getCoordinates())
                .localization(land.getLocalization())
                .build();
    }

    public List<FieldDTO> listAllFields(){
        return landRepository.findAll()
                .stream()
                .map(land -> FieldDTO
                        .builder()
                        .fieldID(land.getFieldID())
                        .name(land.getName())
                        .area(land.getArea())
                        .coordinates(land.getCoordinates())
                        .localization(land.getLocalization())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        landRepository.deleteById(id);
        return ("Land deleted successfully.");
    }

    public String save (){
        landRepository.save(Land.builder().name("field1").area(123).coordinates("bbb").localization("kkk").build());
        landRepository.save(Land.builder().name("field2").area(555).coordinates("xxx").localization("lll").build());
        landRepository.save(Land.builder().name("field3").area(567).coordinates("yyy").localization("ççç").build());
        landRepository.save(Land.builder().name("field4").area(222).coordinates("kkk").localization("hhh").build());
        return "Fields saved successfully";
    }
}
