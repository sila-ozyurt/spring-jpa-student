package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.entities.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Page<Student> findAll(Pageable pageable);

    @Query(value="select * from student.student order by birth_of_date asc",
            countQuery = "select count(*) from student.student",
            nativeQuery = true)
    Page<Student> sortByBirthDate(Pageable pageable);

    @Query(value = "select count(*) from student.student",nativeQuery = true)
    Integer getNumberOfTotalStudents();

    @Query(value="select *from student.student where lower(first_name) like lower(concat('%',:keyword,'%')) ",
            countQuery = "select count(*) from student.student where lower(first_name) like lower(concat('%',:keyword,'%'))",
            nativeQuery = true)
    Page<Student> searchByFirstName(@Param(value="keyword") String keyword,Pageable pageable);

    @Query(value ="select *from student.student s where s.card_id= :cardId" ,nativeQuery = true)
    Optional<Student> getStudentByCardId(@Param(value="cardId") Long cardId);

    @Query(value="""
            select s.*
            from student.student s 
            join student.student_courses sc  on s.id=sc.student_id
            join student.courses c on c.id=sc.course_id
            where c.id=:courseId
            """,
            countQuery = """
                    select count(*)
                    from student.student s
                    join student.student_courses sc on s.id=sc.student_id
                    join student.courses c on c.id=sc.course_id
                    where c.id=:courseId 
                    """, nativeQuery = true)
    Page<Student> findStudentByCourseId(@Param(value="courseId") Long courseId,Pageable pageable);

    @Query(value="""
            select d.department_name, count(s.id) as student_count
            from student.student s
            join student.department d on s.main_department_id=d.id
            group by d.department_name
            """,nativeQuery = true)
    List<Object[]> getStudentCountPerDepartment();

    @Query(value="""
            select *  
            from student.student s
            join student.users u
            on s.user_id=u.id
            where u.username= :username
            """,nativeQuery = true)
    Optional<Student> findByUsername(@Param(value = "username") String username);

    @Query(value = """
            select *
            from student.student s
            where s.main_department_id= :id
            """,
            countQuery = """
            select count(*) 
            from student.student s 
            where s.main_department_id= :id
            """,
            nativeQuery = true)
    Page<Student> getStudentsByDepartment(@Param(value = "id") Long id,Pageable pageable);

}