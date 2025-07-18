package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.RootEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


public interface IStudentCardController {

    public RootEntity<DtoStudentCard> save(DtoStudentCard dtoStudentCard);

    public RootEntity<Optional<DtoStudentCard>> findById(Long id);

    public RootEntity<List<DtoStudentCard>> list();
}
