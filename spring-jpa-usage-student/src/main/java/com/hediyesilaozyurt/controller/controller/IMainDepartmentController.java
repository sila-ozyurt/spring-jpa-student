package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentController {

    public RootEntity<DtoMainDepartment> save(DtoMainDepartment mainDepartment);

    public RootEntity<Optional<DtoMainDepartment>> findById(Long id);

    public RootEntity<List<DtoMainDepartment>> list();

    public RootEntity<DtoMainDepartment> update(Long id,DtoMainDepartment mainDepartment);
}
