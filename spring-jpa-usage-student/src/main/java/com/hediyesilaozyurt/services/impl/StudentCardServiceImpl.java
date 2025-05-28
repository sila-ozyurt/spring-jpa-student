package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.StudentCard;
import com.hediyesilaozyurt.mapper.StudentCardMapper;
import com.hediyesilaozyurt.repository.StudentCardRepository;
import com.hediyesilaozyurt.services.IStudentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCardServiceImpl implements IStudentCardService {

    @Autowired
    private StudentCardRepository studentCardRepository;

    @Autowired
    private StudentCardMapper studentCardMapper;

    @Override
    public Optional<DtoStudent> getStudentByCardId(Long cardId) {
       Optional<StudentCard> studentCard = studentCardRepository.findById(cardId);
       return studentCardMapper.studentCardToDtoStudent(studentCard);
    }
}
