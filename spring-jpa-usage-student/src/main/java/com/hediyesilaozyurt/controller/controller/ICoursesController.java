package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICoursesController {

    public RootEntity<DtoCourses> save(DtoCourses dtoCourse);

    public RootEntity<Page<DtoCourses>> findAll(RestPageableRequest pageableRequest);
}
