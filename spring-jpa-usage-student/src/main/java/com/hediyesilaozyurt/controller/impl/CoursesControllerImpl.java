package com.hediyesilaozyurt.controller.impl;

import com.hediyesilaozyurt.controller.ICoursesController;
import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.entities.RootEntity;
import com.hediyesilaozyurt.services.ICoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/courses")
public class CoursesControllerImpl extends RestBaseController implements ICoursesController {

    @Autowired
    private ICoursesService coursesService;

    @Override
    @PostMapping(path="/save")
    public RootEntity<DtoCourses> save(@RequestBody @Valid DtoCourses dtoCourse) {
        return ok(coursesService.save(dtoCourse));
    }

    @Override
    @GetMapping(path="/list")
    public RootEntity<List<DtoCourses>> list() {
        return ok(coursesService.list());
    }
}
