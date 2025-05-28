package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentCardController;
import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.services.IStudentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/api/studentCard")
public class StudentCardControllerImpl implements IStudentCardController {

    @Autowired
    private IStudentCardService studentCardService;

    @Override
    @GetMapping("/{cardId}")
    public Optional<DtoStudent> getStudentByCardId(@PathVariable(name="cardId") Long cardId) {
        return studentCardService.getStudentByCardId(cardId);
    }
}
