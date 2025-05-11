package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentController {

    public Student saveStudent(Student student);

    public List<Student> list();

    public Optional<Student> findById(Integer id);

    public ResponseEntity<?> delete(Integer id);

    public Student update(Integer id,Student student);
}
