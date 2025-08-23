package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoCourses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICoursesService {

    //CRUD operations
    public DtoCourses save(DtoCourses course);
    public Page<DtoCourses> findAll(Pageable pageable);

}
