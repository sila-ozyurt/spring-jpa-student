package com.hediyesilaozyurt.dto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentCard {

    private Long id;

    @NotBlank(message = "CardNumber field cannot be blank")
    private String cardNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

}
