package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.entities.StudentCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


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

    public Page<DtoStudentCard> toDtoPage(Page<StudentCard> studentCards) {
        return studentCards != null ? studentCards.map(this::toDto) : null;
    }
}
