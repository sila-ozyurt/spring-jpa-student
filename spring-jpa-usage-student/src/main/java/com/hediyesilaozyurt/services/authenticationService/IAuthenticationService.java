package com.hediyesilaozyurt.services.authenticationService;

import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;

public interface IAuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest);

}
