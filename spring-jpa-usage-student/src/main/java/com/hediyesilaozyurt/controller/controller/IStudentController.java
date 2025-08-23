package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.dto.utils.RestPageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IStudentController {

    //CRUD operations
    public RootEntity<DtoStudentResponse> saveStudent(DtoStudentRequest student);
    public RootEntity<Page<DtoStudentResponse>> findAll(RestPageableRequest pageableRequest);
    public RootEntity<DtoStudentResponse> update(Long id, DtoStudentRequest student);
    public RootEntity<DtoStudentResponse> findById(Long id);
    public ResponseEntity<?> delete(Long id);

    //special operations
    public RootEntity<DtoStudentResponse> getStudentByCardId(Long cardId);
    public RootEntity<Page<DtoStudentResponse>> getStudentsByDepartment(Long id,RestPageableRequest pageableRequest);
    public RootEntity<Page<DtoStudentResponse>> findStudentByCourseId(Long courseId,RestPageableRequest pageableRequest);
    public RootEntity<Integer> getNumberOfTotalStudents();

    //search operations
    public RootEntity<DtoStudentResponse> findByUsername(String username);
    public RootEntity<Page<DtoStudentResponse>> searchByFirstName(String name,RestPageableRequest pageableRequest);
    public RootEntity<Page<DtoStudentResponse>> sortByBirthDate(RestPageableRequest pageableRequest);


}
