package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.IStudentCardController;
import com.hediyesilaozyurt.dto.DtoStudentCard;
import com.hediyesilaozyurt.services.IStudentCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api/student-card")
public class StudentCardControllerImpl implements IStudentCardController {

    @Autowired
    private IStudentCardService studentCardService;

    @Override
    public List<DtoStudentCard> list() {
        return studentCardService.list();
    }
    @Override
    @GetMapping("/{id}")
    public Optional<DtoStudentCard> findById(@PathVariable(name="id") Long id) {
        return studentCardService.findById(id);
    }

    @Override
    @PostMapping("/save")
    public DtoStudentCard save(@RequestBody @Valid DtoStudentCard dtoStudentCard) {
        return studentCardService.save(dtoStudentCard);
    }
}
