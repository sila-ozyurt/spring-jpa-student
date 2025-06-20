package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.Student;
import com.hediyesilaozyurt.entities.StudentCard;
import com.hediyesilaozyurt.mapper.StudentCardMapper;
import com.hediyesilaozyurt.repository.StudentCardRepository;
import com.hediyesilaozyurt.services.IStudentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCardServiceImpl implements IStudentCardService {

    @Autowired
    private StudentCardMapper studentCardMapper;

    @Autowired
    private StudentCardRepository studentCardRepository;

    @Override
    public Optional<DtoStudentCard> findById(Long id) {
        return studentCardRepository.findById(id).map(studentCardMapper::toDto);
    }

    @Override
    public DtoStudentCard save(DtoStudentCard dtoStudentCard) {
        StudentCard studentCard=studentCardMapper.toEntity(dtoStudentCard);
        StudentCard dbCard=studentCardRepository.save(studentCard);
        return studentCardMapper.toDto(dbCard);
    }

    @Override
    public List<DtoStudentCard> list() {
        List<StudentCard> studentCards=studentCardRepository.findAll();
        return studentCardMapper.toDtoList(studentCards);
    }
}
