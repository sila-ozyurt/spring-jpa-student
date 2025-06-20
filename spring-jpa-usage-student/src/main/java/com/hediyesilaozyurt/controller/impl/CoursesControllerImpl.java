package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.ICoursesController;
import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.services.ICoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/courses")
public class CoursesControllerImpl implements ICoursesController {

    @Autowired
    private ICoursesService coursesService;

    @Override
    @PostMapping(path="/save")
    public DtoCourses save(@RequestBody @Valid DtoCourses dtoCourse) {
        return coursesService.save(dtoCourse);
    }

    @Override
    @GetMapping(path="/list")
    public List<DtoCourses> list() {
        return coursesService.list();
    }
}
