package com.hediyesilaozyurt.services.authenticationService.impl;

import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.authEntity.Role;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import com.hediyesilaozyurt.jwt.JwtService;
import com.hediyesilaozyurt.repository.authRepository.UserRepository;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(RegisterRequest request){
        //username control
        if(userRepository.existByUsername(request.getUsername())){
            return new AuthenticationResponse(null,null,null,"username has taken before");
        }

        //email control
        if(userRepository.existByEmail(request.getEmail())){
            return new AuthenticationResponse(null,null,null,"email has taken before");
        }

        UserEntity user=new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setIsActive(true);

        userRepository.save(user);

        String jwtToken=jwtService.generateToken(user);

        return new AuthenticationResponse(
                jwtToken,
                user.getUsername(),
                user.getRole().name(),
                "registeration is successfull"
        );
    }

}
