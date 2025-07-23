package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

import java.util.List;
import java.util.Optional;


public interface IStudentCardController {

    public RootEntity<DtoStudentCard> save(DtoStudentCard dtoStudentCard);

    public RootEntity<Optional<DtoStudentCard>> findById(Long id);

    public RootEntity<List<DtoStudentCard>> list();
}
