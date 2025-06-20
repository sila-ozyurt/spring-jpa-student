package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentCard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


public interface IStudentCardController {

    public DtoStudentCard save(DtoStudentCard dtoStudentCard);

    public Optional<DtoStudentCard> findById(Long id);

    public List<DtoStudentCard> list();
}
