package com.hediyesilaozyurt.dto.authDto;

import com.hediyesilaozyurt.entities.authEntity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private Long id;

    @NotBlank(message = "username field cannot bu null,empty or blank")
    private String username;

    @NotBlank(message = "password field cannot be null,empty or blank")
    private String password;

    @NotBlank(message = "email field cannot be null,empty or blank")
    @Email(message = "invalid email format")
    private String email;

    @NotNull(message = "role cannot be null")
    private Role role;

}
