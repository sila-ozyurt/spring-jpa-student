package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.MainDepartment;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentController {

    public DtoMainDepartment save(DtoMainDepartment mainDepartment);

    public Optional<DtoMainDepartment> findById(Long id);

    public List<DtoMainDepartment> list();
}
