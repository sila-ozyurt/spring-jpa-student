package com.hediyesilaozyurt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Courses {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name",nullable = false)
    private String courseName;

    @Column(name="course_description",nullable = false)
    private String courseDescription;

    @Column(name = "semester",nullable = false)
    private String semester;


}
