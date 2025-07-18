package com.hediyesilaozyurt.controller;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.RootEntity;
import com.hediyesilaozyurt.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentController {

    public RootEntity<DtoStudent> saveStudent(DtoStudent student);

    public RootEntity<List<DtoStudent>> list();

    public RootEntity<Optional<DtoStudent>> findById(Long id);

    public ResponseEntity<?> delete(Long id);

    public RootEntity<DtoStudent> update(Long id,DtoStudent student);

    public RootEntity<DtoStudent> getStudentByCardId(Long cardId);

    public RootEntity<List<DtoStudent>> sortByBirthDate();

    public RootEntity<Integer> getNumberOfTotalStudents();

    public RootEntity<List<DtoStudent>> searchByFirstName(String name);

    public RootEntity<List<DtoStudent>> studentsTakingASpecificCourse(Long courseId);

}
