package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import jakarta.transaction.Transactional;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Transactional
    public void updateStudentDtotoEntity(StudentDto studentDto, @MappingTarget Student student);
}
