package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.entities.entities.Courses;
import com.hediyesilaozyurt.mapper.CoursesMapper;
import com.hediyesilaozyurt.repository.respository.CoursesRepository;
import com.hediyesilaozyurt.services.services.ICoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImpl implements ICoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private CoursesMapper coursesMapper;

    @Override
    public DtoCourses save(DtoCourses dtoCourse) {
        Courses course=coursesMapper.toEntity(dtoCourse);
        Courses dbCourse=coursesRepository.save(course);
        return coursesMapper.toDto(dbCourse);
    }

    @Override
    public Page<DtoCourses> findAll(Pageable pageable) {
        Page<Courses> courses=coursesRepository.findAll(pageable);
        return coursesMapper.toDtoPage(courses);
    }
}
