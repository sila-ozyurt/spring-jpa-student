package com.hediyesilaozyurt.services;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.dto.DtoStudentIU;
import com.hediyesilaozyurt.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    public DtoStudent saveStudent(DtoStudentIU student);

    public List<Student> list();

    public Optional<Student> findById(Integer id);

    public boolean existById(Integer id);

    public void delete(Integer id);

    public Student update(Integer id,Student student);

}
