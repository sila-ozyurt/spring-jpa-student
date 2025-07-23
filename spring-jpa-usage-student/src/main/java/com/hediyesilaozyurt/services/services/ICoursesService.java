package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoCourses;

import java.util.List;

public interface ICoursesService {

    public DtoCourses save(DtoCourses course);

    public List<DtoCourses> list();

}
