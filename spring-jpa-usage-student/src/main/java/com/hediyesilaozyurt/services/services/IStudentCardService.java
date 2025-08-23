package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IStudentCardService {

    //CRUD operations
    public DtoStudentCard save(DtoStudentCard studentCard);
    public Optional<DtoStudentCard> findById(Long id);
    public Page<DtoStudentCard> findAll(Pageable pageable);
    public boolean existById(Long id);
    public void delete(Long id);
}
