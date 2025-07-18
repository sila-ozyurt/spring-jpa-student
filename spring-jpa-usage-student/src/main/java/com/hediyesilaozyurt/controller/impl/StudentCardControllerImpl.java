package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentCardController;
import com.hediyesilaozyurt.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.RootEntity;
import com.hediyesilaozyurt.services.IStudentCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api/student-card")
public class StudentCardControllerImpl extends RestBaseController implements IStudentCardController {

    @Autowired
    private IStudentCardService studentCardService;

    @Override
    public RootEntity<List<DtoStudentCard>> list() {
        return ok(studentCardService.list());
    }
    @Override
    @GetMapping("/{id}")
    public RootEntity<Optional<DtoStudentCard>> findById(@PathVariable(name="id") Long id) {
        return ok(studentCardService.findById(id));
    }

    @Override
    @PostMapping("/save")
    public RootEntity<DtoStudentCard> save(@RequestBody @Valid DtoStudentCard dtoStudentCard) {
        return ok(studentCardService.save(dtoStudentCard));
    }
}
