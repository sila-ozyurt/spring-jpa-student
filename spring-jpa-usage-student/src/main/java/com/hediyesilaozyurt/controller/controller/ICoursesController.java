package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

import java.util.List;

public interface ICoursesController {

    public RootEntity<DtoCourses> save(DtoCourses dtoCourse);

    public RootEntity<List<DtoCourses>> list();
}
