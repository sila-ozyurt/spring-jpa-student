package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentIU;
import com.hediyesilaozyurt.entities.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Student toEntity(DtoStudentIU dto){
        return modelMapper.map(dto, Student.class);
    }

    public DtoStudent toDto(Student entity){
        return modelMapper.map(entity, DtoStudent.class)
    }
}
