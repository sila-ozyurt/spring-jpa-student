package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.StudentCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentCardMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentMapper studentMapper;

    //a function to translate from student card to dto Student
    public Optional<DtoStudent> studentCardToDtoStudent(Optional<StudentCard> studentCard){
        return studentCard.map(StudentCard::getStudent)
                .map(studentMapper::toDto);
    }
}
