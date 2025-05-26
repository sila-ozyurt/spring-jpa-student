package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentController {

    public DtoStudent saveStudent(DtoStudent student);

    public List<DtoStudent> list();

    public Optional<DtoStudent> findById(Integer id);

    public ResponseEntity<?> delete(Integer id);

    public DtoStudent update(Integer id,DtoStudent student);

    public List<DtoStudent> sortByBirthDate();

    public Integer getNumberOfTotalStudents();

    public List<DtoStudent> searchByFirstName(String name);
}
