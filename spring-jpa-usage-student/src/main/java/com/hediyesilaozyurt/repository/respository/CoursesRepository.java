package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.entities.entities.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long> {

    public Page<Courses> findAll(Pageable pageable);
}
