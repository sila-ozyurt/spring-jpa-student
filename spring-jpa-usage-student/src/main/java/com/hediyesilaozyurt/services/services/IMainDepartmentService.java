package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentService {

    //CRUD operations
    public DtoMainDepartment save(DtoMainDepartment dtoMainDepartment);
    public List<DtoMainDepartment> list();
    public Optional<DtoMainDepartment> findById(Long id);
    public DtoMainDepartment update(Long id, DtoMainDepartment dtoMainDepartment);

    //special operations
    public Integer getStudentCountByDepartment(Long id);
}
