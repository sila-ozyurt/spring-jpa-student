package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.entities.StudentCard;
import com.hediyesilaozyurt.mapper.StudentCardMapper;
import com.hediyesilaozyurt.repository.respository.StudentCardRepository;
import com.hediyesilaozyurt.services.services.IStudentCardService;
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

    @Override
    public boolean existById(Long id) {
        return studentCardRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        studentCardRepository.deleteById(id);
    }
}
