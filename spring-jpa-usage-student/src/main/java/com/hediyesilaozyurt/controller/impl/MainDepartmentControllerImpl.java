package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IMainDepartmentController;
import com.hediyesilaozyurt.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.MainDepartment;
import com.hediyesilaozyurt.services.IMainDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/api/main-department")
public class MainDepartmentControllerImpl implements IMainDepartmentController {

    @Autowired
    private IMainDepartmentService mainDepartmentService;

    @Override
    @PostMapping("/save")
    public DtoMainDepartment save(@RequestBody @Valid DtoMainDepartment dtoMainDepartment) {
        return mainDepartmentService.save(dtoMainDepartment);
    }

    @Override
    @GetMapping("/{id}")
    public Optional<DtoMainDepartment> findById(@PathVariable(name="id") Long id) {
        return mainDepartmentService.findById(id);
    }

    @Override
    @GetMapping("/list")
    public List<DtoMainDepartment> list() {
        return mainDepartmentService.list();
    }
}
