package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.MainDepartment;
import com.hediyesilaozyurt.entities.RootEntity;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentController {

    public RootEntity<DtoMainDepartment> save(DtoMainDepartment mainDepartment);

    public RootEntity<Optional<DtoMainDepartment>> findById(Long id);

    public RootEntity<List<DtoMainDepartment>> list();
}
