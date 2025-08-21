package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    // CRUD Operations
    public DtoStudentResponse saveStudent(DtoStudentRequest student);
    public Page<DtoStudentResponse> findAll(Pageable pageable);
    public DtoStudentResponse update(Long id, DtoStudentRequest student);
    public Optional<DtoStudentResponse> findById(Long id);
    public boolean existById(Long id);
    public void delete(Long id);

    //special operations
    public Optional<DtoStudentResponse> getStudentByCardId(Long cardId);
    public Page<DtoStudentResponse> getStudentsByDepartment(Long id,Pageable pageable);
    public Page<DtoStudentResponse> findStudentByCourseId(Long id,Pageable pageable);
    public Integer getNumberOfTotalStudents();

    //search operations
    public Optional<DtoStudentResponse> findByUsername(String username);
    public Page<DtoStudentResponse> searchByFirstName(String name,Pageable pageable);
    public Page<DtoStudentResponse> sortByBirthDate(Pageable pageable);










}
