package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IStudentController {

    public RootEntity<DtoStudentResponse> saveStudent(DtoStudentRequest student);

    public RootEntity<List<DtoStudentResponse>> list();

    public RootEntity<DtoStudentResponse> findById(Long id);

    public ResponseEntity<?> delete(Long id);

    public RootEntity<DtoStudentResponse> update(Long id, DtoStudentRequest student);

    public RootEntity<DtoStudentResponse> getStudentByCardId(Long cardId);

    public RootEntity<List<DtoStudentResponse>> sortByBirthDate();

    public RootEntity<Integer> getNumberOfTotalStudents();

    public RootEntity<List<DtoStudentResponse>> searchByFirstName(String name);

    public RootEntity<List<DtoStudentResponse>> studentsTakingASpecificCourse(Long courseId);

    public RootEntity<DtoStudentResponse> getStudentProfile(Authentication authentication);

    public RootEntity<List<DtoStudentResponse>> getStudentsByDepartment(Long id);

}
