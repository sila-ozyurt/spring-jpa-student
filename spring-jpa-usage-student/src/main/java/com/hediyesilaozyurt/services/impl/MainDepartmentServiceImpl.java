package com.hediyesilaozyurt.services.impl;

import com.hediyesilaozyurt.dto.DtoMainDepartment;
import com.hediyesilaozyurt.entities.MainDepartment;
import com.hediyesilaozyurt.mapper.MainDepartmentMapper;
import com.hediyesilaozyurt.repository.MainDepartmentRepository;
import com.hediyesilaozyurt.services.IMainDepartmentService;
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
}
