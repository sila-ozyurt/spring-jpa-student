package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMainDepartmentController {

    public RootEntity<DtoMainDepartment> save(DtoMainDepartment mainDepartment);

    public RootEntity<Optional<DtoMainDepartment>> findById(Long id);

    public RootEntity<RestPageableResponse<DtoMainDepartment>> findAll(RestPageableRequest pageableRequest);

    public RootEntity<DtoMainDepartment> update(Long id,DtoMainDepartment mainDepartment);
}
