package com.hediyesilaozyurt.repository.authRepository;

import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query(value="select * from student.users u where u.username= :userName ",nativeQuery = true)
    Optional<UserEntity> findbyUserName(@Param(value="userName") String userName);

    @Query(value = "select count(*)>0 from student.users u where u.username= :userName", nativeQuery = true)
    boolean existByUsername(@Param(value = "userName") String username);

    @Query(value="select count(*)>0 from student.users u where u.email= :email",nativeQuery = true)
    boolean existByEmail(@Param(value = "email") String email);
}
