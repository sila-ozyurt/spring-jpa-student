package com.hediyesilaozyurt.repository.respository;

import com.hediyesilaozyurt.entities.entities.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard,Long>{
}
