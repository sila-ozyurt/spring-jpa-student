package com.hediyesilaozyurt.services;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentCard;

import java.util.Optional;

public interface IStudentCardService {

    public Optional<DtoStudent> getStudentByCardId(Long cardId);
}
