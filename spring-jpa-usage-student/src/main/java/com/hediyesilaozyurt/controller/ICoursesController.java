package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.entities.RootEntity;

import java.util.List;

public interface ICoursesController {

    public RootEntity<DtoCourses> save(DtoCourses dtoCourse);

    public RootEntity<List<DtoCourses>> list();
}
