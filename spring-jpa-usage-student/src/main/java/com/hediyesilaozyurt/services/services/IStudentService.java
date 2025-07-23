package com.hediyesilaozyurt.services.services;

import com.hediyesilaozyurt.dto.dto.DtoStudent;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    public DtoStudent saveStudent(DtoStudent student);

    public List<DtoStudent> list();

    public Optional<DtoStudent> findById(Long id);

    public DtoStudent getStudentByCardId(Long cardId);

    public boolean existById(Long id);

    public void delete(Long id);

    public DtoStudent update(Long id,DtoStudent student);

    public List<DtoStudent> sortByBirthDate();

    public Integer getNumberOfTotalStudents();

    public List<DtoStudent> searchByFirstName(String name);
    public List<DtoStudent> studentsTakingASpecificCourse(Long id);
}
