package com.hediyesilaozyurt.mapper;

import com.hediyesilaozyurt.dto.DtoCourses;
import com.hediyesilaozyurt.entities.Courses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

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

    public List<DtoCourses> toDtoList(List<Courses> courses){
        return courses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
