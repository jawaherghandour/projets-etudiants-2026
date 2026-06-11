package com.example.employee_managment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name="content" , nullable = false ,length=100)
    private String  content;
    @Column(name="created_at" , nullable = false ,length=100)
    private LocalDateTime createdAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="employee_id")
    private Employee employee;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answers;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "subtheme_id")
    private SubTheme subTheme;


}
