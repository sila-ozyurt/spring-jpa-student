package com.hediyesilaozyurt.repository.authRepository;

import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.authEntity.RefreshToken;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    @Query(value = "select * from student.refresh_token where refresh_token= :token",nativeQuery = true)
    Optional<RefreshToken> findByRefreshToken(String token);

    @Query(value ="select * from student.refresh_token where user_id= :id",nativeQuery = true)
    Optional<RefreshToken> findByUser(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from student.refresh_token where user_id= :id",nativeQuery = true)
    void deleteByUser(Long id);



}
