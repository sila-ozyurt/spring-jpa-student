package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.DtoStudent;
import com.hediyesilaozyurt.entities.entities.Student;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void setupMapper(){

        // Entity to DTO mapping - ID included
        modelMapper.typeMap(Student.class, DtoStudent.class);

        // DTO to Entity mapping - ID skip
        modelMapper.typeMap(DtoStudent.class, Student.class)
                .addMappings(mapper->mapper.skip(Student::setId));
    }

    public Student toEntity(DtoStudent dto){
      // if you use one side relation between student and studentCard u can use the code below, the other lines instead.
        return dto!=null ? modelMapper.map(dto, Student.class):null;

/*
     // if you use 2 sided relation between student and student card u have to use the lines below
        if (dto == null) return null;

        Student student = modelMapper.map(dto, Student.class);

        if (student.getStudentCard() != null) {
            student.getStudentCard().setStudent(student);
        }

        return student;*/
    }

    public DtoStudent toDto(Student entity){
        return entity!=null ? modelMapper.map(entity, DtoStudent.class):null;
    }

    public Student updateEntityFromDto(DtoStudent dto, Student entity){
        if(dto==null || entity==null)return null;

        //copy fields in dto into entity
        modelMapper.map(dto,entity);
        return entity;
    }

    public List<DtoStudent> toDtoList(List<Student> students){
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
