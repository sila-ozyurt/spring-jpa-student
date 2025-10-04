package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IMainDepartmentController;
import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IMainDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/main-department")
public class MainDepartmentControllerImpl extends RestBaseController implements IMainDepartmentController {

    @Autowired
    private IMainDepartmentService mainDepartmentService;

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoMainDepartment> save(@RequestBody @Valid DtoMainDepartment dtoMainDepartment) {
        DtoMainDepartment dto=mainDepartmentService.save(dtoMainDepartment);

        return createResponse(dto);
    }

    //public
    @Override
    @GetMapping("/{id}")
    public RootEntity<Optional<DtoMainDepartment>> findById(@PathVariable(name="id") Long id) {
        Optional<DtoMainDepartment> optionalDtoMainDepartment=mainDepartmentService.findById(id);

        return createResponse(optionalDtoMainDepartment);
    }

    //public
    @Override
    @GetMapping("/list")
    public RootEntity<RestPageableResponse<DtoMainDepartment>> findAll(@ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoMainDepartment> dtoMainDepartments=mainDepartmentService.findAll(pageable);
        RestPageableResponse response=toPageableResponse(dtoMainDepartments,dtoMainDepartments.getContent());

        return createResponse(response);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public RootEntity<DtoMainDepartment> update(@PathVariable(name="id") Long id,
                                                @RequestBody @Valid DtoMainDepartment mainDepartment) {
        DtoMainDepartment dto=mainDepartmentService.update(id,mainDepartment);

        return createResponse(dto);
    }
}
