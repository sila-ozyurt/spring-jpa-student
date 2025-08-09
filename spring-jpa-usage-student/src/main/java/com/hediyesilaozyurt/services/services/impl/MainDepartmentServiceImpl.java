package com.hediyesilaozyurt.services.services.impl;

import com.hediyesilaozyurt.dto.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.entities.MainDepartment;
import com.hediyesilaozyurt.mapper.MainDepartmentMapper;
import com.hediyesilaozyurt.repository.respository.MainDepartmentRepository;
import com.hediyesilaozyurt.services.services.IMainDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainDepartmentServiceImpl implements IMainDepartmentService {

    @Autowired
    private MainDepartmentMapper mainDepartmentMapper;

    @Autowired
    private MainDepartmentRepository mainDepartmentRepository;

    @Override
    public DtoMainDepartment save(DtoMainDepartment dtoMainDepartment) {
        MainDepartment mainDepartment=mainDepartmentMapper.toEntity(dtoMainDepartment);
        MainDepartment dbMainDepartment=mainDepartmentRepository.save(mainDepartment);
        return mainDepartmentMapper.toDto(dbMainDepartment);
    }

    @Override
    public List<DtoMainDepartment> list() {
        List<MainDepartment> mainDepartments=mainDepartmentRepository.findAll();
        return mainDepartmentMapper.toDtoList(mainDepartments);
    }

    @Override
    public Optional<DtoMainDepartment> findById(Long id) {
        return mainDepartmentRepository.findById(id).map(mainDepartmentMapper::toDto);
    }

    @Override
    public DtoMainDepartment update(Long id, DtoMainDepartment dtoMainDepartment) {
       MainDepartment dbMainDepartment=mainDepartmentRepository.findById(id).get();
       MainDepartment entityToUpdate=mainDepartmentMapper.updateEntityFromDto(dtoMainDepartment,dbMainDepartment);
       return mainDepartmentMapper.toDto(mainDepartmentRepository.save(entityToUpdate));
    }

    @Override
    public Integer getStudentCountByDepartment(Long id) {
        return mainDepartmentRepository.getStudentCountByDepartment(id);
    }

}
