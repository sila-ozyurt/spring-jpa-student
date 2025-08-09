package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IMainDepartmentController;
import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IMainDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(dto!=null){
            return ok(dto);
        }
        else{
            return error(dto);
        }
    }

    //public
    @Override
    @GetMapping("/{id}")
    public RootEntity<Optional<DtoMainDepartment>> findById(@PathVariable(name="id") Long id) {
        Optional<DtoMainDepartment> optionalDtoMainDepartment=mainDepartmentService.findById(id);

        if(optionalDtoMainDepartment.isPresent()){
            return ok(optionalDtoMainDepartment);
        }
        else{
            return error(optionalDtoMainDepartment);
        }
    }

    //public
    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoMainDepartment> > list() {
        List<DtoMainDepartment> dtoMainDepartments=mainDepartmentService.list();

        if(dtoMainDepartments!=null){
            return ok(dtoMainDepartments);
        }else{
            return error(dtoMainDepartments);
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public RootEntity<DtoMainDepartment> update(@PathVariable(name="id") Long id,
                                                @RequestBody @Valid DtoMainDepartment mainDepartment) {
        DtoMainDepartment dto=mainDepartmentService.update(id,mainDepartment);

        if(dto!=null){
            return ok(dto);
        }
        else{
            return error(dto);
        }
    }
}
