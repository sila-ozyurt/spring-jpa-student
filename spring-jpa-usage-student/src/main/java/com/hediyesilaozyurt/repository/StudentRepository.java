package com.hediyesilaozyurt.repository;

import com.hediyesilaozyurt.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value="select * from student.student order by birth_of_date asc",nativeQuery = true)
    List<Student> sortByBirthDate();

    @Query(value = "select count(*) from student.student",nativeQuery = true)
    Integer getNumberOfTotalStudents();

    @Query(value="select *from student.student where lower(first_name) like lower(concat('%',:keyword,'%')) ",nativeQuery = true)
    List<Student> searchByFirstName(@Param(value="keyword") String keyword);
}
