package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.controller.ICoursesController;
import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.dto.utils.RestPageableResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.services.ICoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/courses")
public class CoursesControllerImpl extends RestBaseController implements ICoursesController {

    @Autowired
    private ICoursesService coursesService;

    @Override
    @PostMapping(path="/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<DtoCourses> save(@RequestBody @Valid DtoCourses dtoCourse) {
        DtoCourses savedCourse=coursesService.save(dtoCourse);

        return createResponse(savedCourse);
    }

    //public
    @Override
    @GetMapping(path="/list")
    public RootEntity<RestPageableResponse<DtoCourses>> findAll(@ModelAttribute RestPageableRequest pageableRequest) {
        Pageable pageable=toPageAble(pageableRequest);
        Page<DtoCourses> dtoCourses=coursesService.findAll(pageable);
        RestPageableResponse response=toPageableResponse(dtoCourses,dtoCourses.getContent());

        return createResponse(response);
    }

}
