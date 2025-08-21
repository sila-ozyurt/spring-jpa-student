package com.hediyesilaozyurt.services.authenticationService;

import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.authEntity.RefreshToken;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;

import java.util.Optional;

public interface IRefreshTokenService {
    
    public RefreshToken createRefreshToken(Long id);

    public Optional<RefreshToken> findByToken(RefreshTokenRequest token);

    public RefreshToken verifyExpiration(RefreshToken token);

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);

}

   
