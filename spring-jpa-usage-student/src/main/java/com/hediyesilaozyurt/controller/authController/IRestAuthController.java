package com.hediyesilaozyurt.controller.authController;

import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestAuthController {

    public RootEntity<AuthenticationResponse> register(RegisterRequest request);

    public RootEntity<AuthenticationResponse> authenticate(AuthenticationRequest authRequest);

    public RootEntity<AuthenticationResponse> refreshToken(RefreshTokenRequest token);
}
