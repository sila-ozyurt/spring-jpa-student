package com.hediyesilaozyurt.entities.authEntity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="refresh_token",unique = true,nullable = false,length=500)
    private String refreshToken;

    @Column(name="expiry_date",nullable = false)
    private LocalDateTime expiryDate;

    @OneToOne
    @JoinColumn(name="user_id",
            nullable = false)
    private UserEntity user;


    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
