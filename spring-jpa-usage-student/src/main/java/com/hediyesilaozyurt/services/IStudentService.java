package com.hediyesilaozyurt.services;

import com.hediyesilaozyurt.dto.DtoStudent;
import com.hediyesilaozyurt.entities.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService {

    public DtoStudent saveStudent(DtoStudent student);

    public List<DtoStudent> list();

    public Optional<DtoStudent> findById(Integer id);

    public boolean existById(Integer id);

    public void delete(Integer id);

    public DtoStudent update(Integer id,DtoStudent student);

    public Optional<Student> findEntityById(Integer id);

    public List<DtoStudent> sortByBirthDate();

    public Integer getNumberOfTotalStudents();

    public List<DtoStudent> searchByFirstName(String name);
}
