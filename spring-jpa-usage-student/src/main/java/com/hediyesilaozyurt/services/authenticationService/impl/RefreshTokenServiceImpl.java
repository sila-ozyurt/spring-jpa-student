package com.hediyesilaozyurt.services.authenticationService.impl;

import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.entities.authEntity.RefreshToken;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import com.hediyesilaozyurt.jwt.JwtService;
import com.hediyesilaozyurt.repository.authRepository.RefreshTokenRepository;
import com.hediyesilaozyurt.repository.authRepository.UserRepository;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    //7 days
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenDurationMs;

    public RefreshToken createRefreshToken(Long userId){

        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found: "+userId));

        refreshTokenRepository.deleteByUser(user.getId());

        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs/1000));
        refreshToken.setUser(user);

        refreshToken=refreshTokenRepository.save(refreshToken);
        log.info("created new refresh token for user: {}"+user.getUsername());

        return refreshToken;
    }

    @Override
    public Optional<RefreshToken> findByToken(RefreshTokenRequest token) {
        return refreshTokenRepository.findByRefreshToken(token.getToken());
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.isExpired()){
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh Token is expired. Please login again");
        }
        return token;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        return findByToken(refreshToken)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user->{
                    String accessToken=jwtService.generateToken(user);
                    RefreshToken renewedRefreshToken=refreshTokenRepository.save(createRefreshToken(user.getId()));
                    return new AuthenticationResponse(
                            accessToken,
                            renewedRefreshToken.getRefreshToken(),
                            "Bearer",
                            user.getUsername(),
                            user.getRole().name(),
                            "Token refreshed successfully"
                    );
                })
                .orElseThrow(()->new RuntimeException("Refresh Token not found: "+refreshToken));
    }
}
