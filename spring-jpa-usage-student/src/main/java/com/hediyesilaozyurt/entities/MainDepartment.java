package com.hediyesilaozyurt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="department",schema="student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="department_name",nullable = false)
    private String departmentName;

    @Column(name="faculty_name",nullable = false)
    private String facultyName;

    @Column(name="establish_year",nullable = false)
    @Past
    private Date establishYear;

}
