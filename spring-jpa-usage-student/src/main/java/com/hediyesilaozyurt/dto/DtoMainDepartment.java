package com.hediyesilaozyurt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMainDepartment {

    private Long id;

    @Size(min = 3,max=25)
    @NotBlank(message = "department name cannot be null,empty or blank")
    private String departmentName;

    @Size(min=3,max=20)
    @NotBlank(message = "faculty name cannot be null, empty or blank")
    private String facultyName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date establishYear;

}
