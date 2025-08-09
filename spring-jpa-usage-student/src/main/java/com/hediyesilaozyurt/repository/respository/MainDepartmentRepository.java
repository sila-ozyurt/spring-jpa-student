package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.entities.entities.MainDepartment;
import com.hediyesilaozyurt.entities.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainDepartmentRepository extends JpaRepository<MainDepartment,Long> {

    @Query(value = """
            select count(*)
            from student.student 
            where main_department_id= :id""",nativeQuery = true)
    Integer getStudentCountByDepartment(@Param(value="id") Long id);
}
