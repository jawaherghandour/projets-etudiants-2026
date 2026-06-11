package com.example.employee_managment.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name="first_name" , nullable = false ,length=100)
    private String firstName;
    @Column(name="last_name" , nullable = false ,length=100)
    private String lastName;
    @Column(name="email" , nullable = false ,unique = true)
    private String email;
    @Column(name="phone_number"  ,length=100)
    private String phoneNumber;
    @Column(name="hire_date" , nullable = false )
    private LocalDate hireDate;
    @Column(name="position" , nullable = false )
    private String position;
    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name="speciality_id" , nullable = false )
    private Speciality speciality;
     @OneToMany(mappedBy = "employee")
     private List<Answer> answers;
    @OneToMany(mappedBy = "employee")
    private List<Question> questions;


}
