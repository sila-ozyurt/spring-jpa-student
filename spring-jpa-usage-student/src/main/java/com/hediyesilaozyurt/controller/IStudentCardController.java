package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


public interface IStudentCardController {

    public Optional<DtoStudent> getStudentByCardId(Long cardId);
}
