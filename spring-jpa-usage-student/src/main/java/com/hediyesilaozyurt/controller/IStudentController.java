package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentController {

    public DtoStudent saveStudent(DtoStudent student);

    public List<DtoStudent> list();

    public Optional<DtoStudent> findById(Long id);

    public ResponseEntity<?> delete(Long id);

    public DtoStudent update(Long id,DtoStudent student);

    public DtoStudent getStudentByCardId(Long cardId);

    public List<DtoStudent> sortByBirthDate();

    public Integer getNumberOfTotalStudents();

    public List<DtoStudent> searchByFirstName(String name);

    public List<DtoStudent> studentsTakingASpecificCourse(Long courseId);

}
