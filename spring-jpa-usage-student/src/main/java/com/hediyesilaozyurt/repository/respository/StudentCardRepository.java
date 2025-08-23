package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.dto.dto.DtoStudentCard;
import com.hediyesilaozyurt.entities.entities.StudentCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard,Long>{

    public Page<StudentCard> findAll(Pageable pageable);
}
