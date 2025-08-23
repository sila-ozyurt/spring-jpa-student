package com.hediyesilaozyurt.mapper;


import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.entities.MainDepartment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainDepartmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DtoMainDepartment toDto(MainDepartment mainDepartment){
        return mainDepartment!=null ? modelMapper.map(mainDepartment, DtoMainDepartment.class):null;
    }

    public MainDepartment toEntity(DtoMainDepartment dtoMainDepartment){
        return dtoMainDepartment!=null ?modelMapper.map(dtoMainDepartment, MainDepartment.class):null;
    }

    public Page<DtoMainDepartment> toDtoPage(Page<MainDepartment> mainDepartments){
        return mainDepartments!=null? mainDepartments.map(this::toDto):null;
    }

    public MainDepartment updateEntityFromDto(DtoMainDepartment dto,MainDepartment entity){
        if(dto==null || entity==null){
            return null;
        }

        //copy fields in dto into entity
        modelMapper.map(dto,entity);
        return entity;
    }
}