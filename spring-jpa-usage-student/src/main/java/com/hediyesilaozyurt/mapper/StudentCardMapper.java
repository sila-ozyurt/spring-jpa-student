package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.StudentCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCardMapper {

    @Autowired
    private ModelMapper modelMapper;

    public StudentCard toEntity(DtoStudentCard dtoStudentCard){
        return dtoStudentCard!=null ? modelMapper.map(dtoStudentCard, StudentCard.class):null;
    }

    public DtoStudentCard toDto(StudentCard studentCard){
        return studentCard!=null ? modelMapper.map(studentCard, DtoStudentCard.class):null;
    }

    public List<DtoStudentCard> toDtoList(List<StudentCard> studentCards){
        return studentCards.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
