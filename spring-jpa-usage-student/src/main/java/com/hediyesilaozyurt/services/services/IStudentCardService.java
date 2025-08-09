package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;

import java.util.List;
import java.util.Optional;

public interface IStudentCardService {

    public DtoStudentCard save(DtoStudentCard studentCard);

    public Optional<DtoStudentCard> findById(Long id);

    public List<DtoStudentCard> list();

    public boolean existById(Long id);

    public void delete(Long id);
}
