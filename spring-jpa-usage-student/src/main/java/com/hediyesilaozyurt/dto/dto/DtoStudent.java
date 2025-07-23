package com.hediyesilaozyurt.dto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudent {

    private Long id;

    @Size(min = 11,max = 11)
    @Pattern(regexp = "\\d{11}",message = "tckn must contain only digits")
    private String tckn;

    @Size(min = 3,max = 20)
    @NotBlank(message = "firstname field cannot be null, empty or blank")
    private String firstName;

    @Size(min = 2,max = 25)
    @NotBlank(message = "lastname field cannot be null empty or blank")
    private String lastName;

    @NotNull(message = "cannot be null")
    @Past
    private Date birthOfDate;

    @NotBlank(message = "email field cannot be null,empty or blank")
    @Email(message = "invalid email format")
    private String email;

    private DtoStudentCard studentCard;

    private DtoMainDepartment mainDepartment;

    private List<DtoCourses> courses;
}
