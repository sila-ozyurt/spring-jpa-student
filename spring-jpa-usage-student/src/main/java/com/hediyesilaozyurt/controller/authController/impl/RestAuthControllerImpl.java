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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/auth")
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

    @Autowired
    private IAuthenticationService authenticationService;

    //public
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

    //public
    @PostMapping("/login")
    @Override
    public RootEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
        AuthenticationResponse response=authenticationService.authenticate(authRequest);

        if (response.getToken()!=null){
            return ok(response);
        }else{
            return error(response);
        }

    }
/*
    // Kullanıcı kendi profilini görebilir
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<Object> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        return ok(authenticationService.getUserProfile(username));
    }

    // Kullanıcı şifresini değiştirebilir
    @PutMapping("/change-password")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<String> changePassword(@RequestBody ChangePasswordRequest request,
                                             Authentication authentication) {
        String username = authentication.getName();
        boolean success = authenticationService.changePassword(username, request.getOldPassword(), request.getNewPassword());

        if (success) {
            return ok("Password successfully changed");
        } else {
            return error("Failed to change password. Please check your current password.");
        }
    }

    // Token yenileme
    @PostMapping("/refresh-token")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<AuthenticationResponse> refreshToken(Authentication authentication) {
        String username = authentication.getName();
        AuthenticationResponse response = authenticationService.refreshToken(username);
        return ok(response);
    }

    // Çıkış yapma (token'ı blacklist'e alma)
    @PostMapping("/logout")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<String> logout(@RequestHeader("Authorization") String token,
                                     Authentication authentication) {
        String username = authentication.getName();
        authenticationService.logout(token, username);
        return ok("Successfully logged out");
    }

    // Sadece Admin tüm kullanıcıları görebilir
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<List<Object>> getAllUsers() {
        return ok(authenticationService.getAllUsers());
    }

    // Sadece Admin kullanıcı rolünü değiştirebilir
    @PutMapping("/users/{userId}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<String> updateUserRole(@PathVariable Long userId, @RequestParam String role) {
        boolean success = authenticationService.updateUserRole(userId, role);

        if (success) {
            return ok("User role successfully updated to: " + role);
        } else {
            return error("Failed to update user role");
        }
    }

    // Sadece Admin kullanıcıyı aktif/pasif yapabilir
    @PutMapping("/users/{userId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public RootEntity<String> updateUserStatus(@PathVariable Long userId, @RequestParam boolean active) {
        boolean success = authenticationService.updateUserStatus(userId, active);

        if (success) {
            String status = active ? "activated" : "deactivated";
            return ok("User successfully " + status);
        } else {
            return error("Failed to update user status");
        }
    }

    // Kullanıcının rolünü kontrol etme
    @GetMapping("/check-role")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public RootEntity<String> checkUserRole(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return ok("Your role is: " + role);
    }

    // Email doğrulama (eğer email doğrulama sisteminiz varsa)
    @PostMapping("/verify-email")
    public RootEntity<String> verifyEmail(@RequestParam String token) {
        boolean success = authenticationService.verifyEmail(token);

        if (success) {
            return ok("Email successfully verified");
        } else {
            return error("Invalid or expired verification token");
        }
    }

    // Şifremi unuttum
    @PostMapping("/forgot-password")
    public RootEntity<String> forgotPassword(@RequestParam String email) {
        boolean success = authenticationService.sendPasswordResetEmail(email);

        if (success) {
            return ok("Password reset email sent successfully");
        } else {
            return error("Email not found or failed to send reset email");
        }
    }

    // Şifre sıfırlama
    @PostMapping("/reset-password")
    public RootEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean success = authenticationService.resetPassword(token, newPassword);

        if (success) {
            return ok("Password successfully
    */
}
