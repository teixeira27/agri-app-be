package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Field;
import org.acme.dto.FieldDTO;
import org.acme.repository.FieldRepository;
import java.util.List;

@ApplicationScoped
public class FieldService {

    @Inject
    FieldRepository fieldRepository;

    public String createField (FieldDTO fieldDTO){
        fieldRepository.save(Field.builder()
                .name(fieldDTO.getName())
                .area(fieldDTO.getArea())
                .coordinates(fieldDTO.getCoordinates())
                .localization(fieldDTO.getLocalization())
                .build());
        return "Field saved successfully.";
    }

    public FieldDTO findById (Integer id){
        final Field field = fieldRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("Field not found."));
        return FieldDTO.builder()
                .fieldID(field.getFieldID())
                .name(field.getName())
                .area(field.getArea())
                .coordinates(field.getCoordinates())
                .localization(field.getLocalization())
                .build();
    }

    public List<FieldDTO> listAllFields(){
        return fieldRepository.findAll()
                .stream()
                .map(field -> FieldDTO
                        .builder()
                        .fieldID(field.getFieldID())
                        .name(field.getName())
                        .area(field.getArea())
                        .coordinates(field.getCoordinates())
                        .localization(field.getLocalization())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        fieldRepository.deleteById(id);
        return ("Field deleted successfully.");
    }

    public String save (){
        fieldRepository.save(Field.builder().name("field1").area(123).coordinates("bbb").localization("kkk").build());
        fieldRepository.save(Field.builder().name("field2").area(555).coordinates("xxx").localization("lll").build());
        fieldRepository.save(Field.builder().name("field3").area(567).coordinates("yyy").localization("ççç").build());
        fieldRepository.save(Field.builder().name("field4").area(222).coordinates("kkk").localization("hhh").build());
        return "Fields saved successfully";
    }
}
