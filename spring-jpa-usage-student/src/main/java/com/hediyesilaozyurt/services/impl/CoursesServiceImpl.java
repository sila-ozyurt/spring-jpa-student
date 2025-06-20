package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Courses;
import com.hediyesilaozyurt.mapper.CoursesMapper;
import com.hediyesilaozyurt.repository.CoursesRepository;
import com.hediyesilaozyurt.services.ICoursesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DtoCourses> list() {
        List<Courses> courses=coursesRepository.findAll();
        return coursesMapper.toDtoList(courses);
    }
}
