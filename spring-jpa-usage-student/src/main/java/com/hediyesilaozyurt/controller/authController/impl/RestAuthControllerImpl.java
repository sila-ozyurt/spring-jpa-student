package com.hediyesilaozyurt.controller.authController.impl;

import com.hediyesilaozyurt.controller.authController.IRestAuthController;
import com.hediyesilaozyurt.controller.controller.impl.RestBaseController;
import com.hediyesilaozyurt.dto.authDto.AuthenticationRequest;
import com.hediyesilaozyurt.dto.authDto.AuthenticationResponse;
import com.hediyesilaozyurt.dto.authDto.RegisterRequest;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import com.hediyesilaozyurt.services.authenticationService.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/auth")
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request){
       AuthenticationResponse response=authenticationService.register(request);

       if(response.getToken()!=null){
           return ok(response);
       }
       else{
           return error(response);
       }
    }
}
