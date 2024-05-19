package com.iuh.courseservice.service;

import com.iuh.courseservice.dto.FieldRp;
import com.iuh.courseservice.dto.FieldRq;
import com.iuh.courseservice.model.Field;
import com.iuh.courseservice.repository.FieldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {
    @Autowired
    public FieldRepo fieldRepo;
    public FieldRp mapEachOfList(Field field){
        return FieldRp.builder()
                .fieldName(field.getFieldName())
                .build();
    }

    public List<FieldRp> getAllField(){
        return fieldRepo.findAll().stream().map(this::mapEachOfList).toList();
    }
    public FieldRq addNewField(FieldRq fieldRq){
        Field field = Field.builder()
                .fieldName(fieldRq.getFieldName())
                .build();
        fieldRepo.save(field);
        return  fieldRq;
    }
    public FieldRp getOneById(int id){
        Optional<Field> fieldOptional = fieldRepo.findById(id);
        if (fieldOptional.isPresent()) {
            Field field = fieldOptional.get();
            FieldRp fieldRp = FieldRp.builder()
                    .fieldName(field.getFieldName())
                    .build();
        }
        return null;
    }
}