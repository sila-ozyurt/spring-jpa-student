package com.hediyesilaozyurt.controller.authController.impl;

import com.hediyesilaozyurt.controller.authController.IRestAuthController;
import com.hediyesilaozyurt.controller.controller.impl.RestBaseController;
import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RefreshTokenRequest;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import com.hediyesilaozyurt.services.authenticationService.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/auth")
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    //public
    @PostMapping("/register")
    @Override
    public RootEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);

        if (response.getAccessToken() != null) {
            return ok(response);
        } else {
            return error(response);
        }
    }

    //public
    @PostMapping("/login")
    @Override
    public RootEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
        AuthenticationResponse response = authenticationService.authenticate(authRequest);

        if (response.getAccessToken() != null) {
            return ok(response);
        } else {
            return error(response);
        }

    }

    // public
    @PostMapping("/refresh-token")
    @Override
    public RootEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest token) {
        AuthenticationResponse response =refreshTokenService.refreshToken(token);

        if(response!=null){
            return ok(response);
        }
        else{
            return error(response);
        }
    }
}