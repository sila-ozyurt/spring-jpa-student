package com.hediyesilaozyurt.dto.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCourses {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("courseName")
    @NotBlank(message = "course name cannot be null, empty or blank")
    @Size(min=3,max=28)
    private String courseName;

    @JsonProperty("courseDescription")
    @Size(min=5)
    @NotBlank(message = "course description area cannot be null,empty or blank")
    private String courseDescription;

    @JsonProperty("semester")
    @NotBlank(message = "semester cannot be null, empty or blank")
    private String semester;

}
