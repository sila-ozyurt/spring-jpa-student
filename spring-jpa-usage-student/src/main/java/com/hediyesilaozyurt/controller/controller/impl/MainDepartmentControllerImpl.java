package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.IMainDepartmentController;
import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.IMainDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RootEntity<DtoMainDepartment> save(@RequestBody @Valid DtoMainDepartment dtoMainDepartment) {
        return ok(mainDepartmentService.save(dtoMainDepartment));
    }

    @Override
    @GetMapping("/{id}")
    public RootEntity<Optional<DtoMainDepartment>> findById(@PathVariable(name="id") Long id) {
        return ok(mainDepartmentService.findById(id));
    }

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoMainDepartment> > list() {
        return ok(mainDepartmentService.list());
    }
}
