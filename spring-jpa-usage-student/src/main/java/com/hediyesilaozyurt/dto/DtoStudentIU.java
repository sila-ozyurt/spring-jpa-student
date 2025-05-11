package com.hediyesilaozyurt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

    private String firstName;

    private String lastName;

    private Date birthOfDate;
}
