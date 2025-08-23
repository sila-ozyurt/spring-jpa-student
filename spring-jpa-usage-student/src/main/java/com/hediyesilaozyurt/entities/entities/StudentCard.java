package com.hediyesilaozyurt.entities.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "student_card",schema = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="card_number",nullable = false,unique = true)
    @JsonProperty("cardNumber")
    private String cardNumber;

    @Column(name = "issue_date",nullable = false)
    private Date issueDate;




}
