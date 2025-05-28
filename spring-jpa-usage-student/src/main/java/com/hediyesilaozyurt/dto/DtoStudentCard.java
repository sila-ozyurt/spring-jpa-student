package com.hediyesilaozyurt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentCard {

    private Long id;

    private String cardNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

}
