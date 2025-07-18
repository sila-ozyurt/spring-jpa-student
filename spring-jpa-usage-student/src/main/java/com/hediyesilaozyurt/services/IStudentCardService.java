package com.hediyesilaozyurt.services;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentCard;

import java.util.List;
import java.util.Optional;

public interface IStudentCardService {

    public DtoStudentCard save(DtoStudentCard studentCard);

    public Optional<DtoStudentCard> findById(Long id);

    public List<DtoStudentCard> list();
}
