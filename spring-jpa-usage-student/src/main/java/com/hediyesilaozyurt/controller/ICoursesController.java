package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoCourses;

import java.util.List;

public interface ICoursesController {

    public DtoCourses save(DtoCourses dtoCourse);

    public List<DtoCourses> list();
}
