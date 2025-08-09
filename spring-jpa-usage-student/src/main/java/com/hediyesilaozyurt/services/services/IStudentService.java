package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoStudentRequest;
import com.hediyesilaozyurt.dto.dto.DtoStudentResponse;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    public DtoStudentResponse saveStudent(DtoStudentRequest student);

    public List<DtoStudentResponse> list();

    public Optional<DtoStudentResponse> findById(Long id);

    public Optional<DtoStudentResponse> getStudentByCardId(Long cardId);

    public boolean existById(Long id);

    public void delete(Long id);

    public DtoStudentResponse update(Long id, DtoStudentRequest student);

    public List<DtoStudentResponse> sortByBirthDate();

    public Integer getNumberOfTotalStudents();

    public List<DtoStudentResponse> searchByFirstName(String name);

    public List<DtoStudentResponse> studentsTakingASpecificCourse(Long id);

    public Optional<DtoStudentResponse> findByUsername(String username);

    public List<DtoStudentResponse> getStudentsByDepartment(Long id);
}
