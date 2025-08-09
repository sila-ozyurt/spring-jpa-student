package com.hediyesilaozyurt.entities.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hediyesilaozyurt.entities.authEntity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student",schema = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tckn",nullable = false,unique = true)
    private String tckn;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    /*@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)*/
    @Column(name = "birth_of_date",nullable = true)
    @Past
    private Date birthOfDate;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="card_id",nullable = false)
    private StudentCard studentCard;

    @ManyToOne
    @JoinColumn(name="main_department_id",nullable = false)
    private MainDepartment mainDepartment;

    @ManyToMany
    @JoinTable(name="student_courses"
            ,joinColumns =@JoinColumn(name="student_id")
            ,inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Courses> courses;

    @OneToOne(cascade = CascadeType.MERGE,fetch =FetchType.LAZY)
    @JoinColumn(name="user_id",unique = true,nullable = false)
    private UserEntity user;
}