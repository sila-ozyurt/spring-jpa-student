package com.hediyesilaozyurt.controller.authController;

import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

public interface IRestAuthController {

    public RootEntity<AuthenticationResponse> register(RegisterRequest request);
}
