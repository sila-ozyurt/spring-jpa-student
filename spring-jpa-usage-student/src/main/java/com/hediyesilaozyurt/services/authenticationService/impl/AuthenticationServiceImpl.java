package com.hediyesilaozyurt.services.authenticationService.impl;

import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.authEntity.RefreshToken;
import com.hediyesilaozyurt.entities.authEntity.Role;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import com.hediyesilaozyurt.jwt.JwtService;
import com.hediyesilaozyurt.repository.authRepository.RefreshTokenRepository;
import com.hediyesilaozyurt.repository.authRepository.UserRepository;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Override
    public AuthenticationResponse register(RegisterRequest request){
        //username control
        if(userRepository.existByUsername(request.getUsername())){
            return new AuthenticationResponse(null,null,null,null,null,"username has taken before");
        }

        //email control
        if(userRepository.existByEmail(request.getEmail())){
            return new AuthenticationResponse(null,null,null,null,null,"email has taken before");
        }

        UserEntity user=new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setIsActive(true);

        userRepository.save(user);
/*
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", user.getEmail());
        String jwtToken=jwtService.generateToken(extraClaims, user);*/

        String jwtToken=jwtService.generateToken(user);

        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user.getId());

        return new AuthenticationResponse(
                jwtToken,
                refreshToken.getRefreshToken(),
                "Bearer",
                user.getUsername(),
                user.getRole().name(),
                "registeration is successfull"
        );
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
       try {
           Authentication authentication=authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           authRequest.getUsername(),
                           authRequest.getPassword()
                   )
           );

           //get user from the db
           UserEntity user=userRepository.findbyUserName(authRequest.getUsername())
                   .orElseThrow(()->new UsernameNotFoundException("user not found"));

           //create token
           String accessToken=jwtService.generateToken(user);
           RefreshToken refreshToken=refreshTokenService.createRefreshToken(user.getId());

           return new AuthenticationResponse(
                   accessToken,
                   refreshToken.getRefreshToken(),
                   "Bearer",
                   user.getUsername(),
                   user.getRole().name(),
                   "Entrance is successfull"
           );


       } catch (Exception e) {
           return new AuthenticationResponse(
                   null,
                   null,
                   null,
                   null,
                   null,
                   "invalid username or password "
           );
       }
    }

}
