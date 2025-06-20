package com.hediyesilaozyurt.services;

import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.dto.DtoStudent;

import java.util.List;

public interface ICoursesService {

    public DtoCourses save(DtoCourses course);

    public List<DtoCourses> list();

}
