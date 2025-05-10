package com.hediyesilaozyurt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "student",schema = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name="id")
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("firstName")
    @Column(name="first_name",nullable = false)
    private String firstName;

    @JsonProperty("lastName")
    @Column(name="last_name",nullable = false)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthOfDate")
    /*@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)*/
    @Column(name = "birth_of_date",nullable = true)
    private Date birthOfDate;

}
