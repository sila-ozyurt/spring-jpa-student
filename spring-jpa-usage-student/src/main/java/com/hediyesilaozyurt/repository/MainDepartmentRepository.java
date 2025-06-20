package com.hediyesilaozyurt.repository;

import com.hediyesilaozyurt.entities.MainDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainDepartmentRepository extends JpaRepository<MainDepartment,Long> {
}
