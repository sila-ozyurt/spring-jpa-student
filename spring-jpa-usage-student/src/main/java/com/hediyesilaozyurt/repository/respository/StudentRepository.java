package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.entities.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value="select * from student.student order by birth_of_date asc",nativeQuery = true)
    List<Student> sortByBirthDate();

    @Query(value = "select count(*) from student.student",nativeQuery = true)
    Integer getNumberOfTotalStudents();

    @Query(value="select *from student.student where lower(first_name) like lower(concat('%',:keyword,'%')) ",nativeQuery = true)
    List<Student> searchByFirstName(@Param(value="keyword") String keyword);

    @Query(value ="select *from student.student s where s.card_id= :cardId" ,nativeQuery = true)
    Optional<Student> getStudentByCardId(@Param(value="cardId") Long cardId);

    @Query(value="""
            select s.*
            from student.student s 
            join student.student_courses sc  on s.id=sc.student_id
            join student.courses c on c.id=sc.course_id
            where c.id=:courseId
            """,nativeQuery = true)
    List<Student> findStudentByCourseId(@Param(value="courseId") Long courseId);

    @Query(value="""
            select d.department_name, count(s.id) as student_count
            from student.student s
            join student.department d on s.main_department_id=d.id
            group by d.department_name
            """,nativeQuery = true)
    List<Object[]> getStudentCountPerDepartment();


}