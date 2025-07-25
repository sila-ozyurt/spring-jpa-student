package com.hediyesilaozyurt.dto.authDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String username;

    @NotBlank
    private String role;

    @NotBlank
    private String message;
}
