package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.dto.DtoCourses;
import com.hediyesilaozyurt.entities.entities.Courses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoursesMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DtoCourses toDto(Courses course){
        return course!=null ? modelMapper.map(course, DtoCourses.class):null;
    }

    public Courses toEntity(DtoCourses dtoCourse){
        return dtoCourse!=null ?modelMapper.map(dtoCourse, Courses.class):null;
    }

    public Page<DtoCourses> toDtoPage(Page<Courses> courses){
        return courses!=null?courses.map(this::toDto):null;
    }
}
