package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentService {

    //CRUD operations
    public DtoMainDepartment save(DtoMainDepartment dtoMainDepartment);
    public Page<DtoMainDepartment> findAll(Pageable pageable);
    public Optional<DtoMainDepartment> findById(Long id);
    public DtoMainDepartment update(Long id, DtoMainDepartment dtoMainDepartment);

    //special operations
    public Integer getStudentCountByDepartment(Long id);
}
