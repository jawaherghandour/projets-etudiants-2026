package com.example.employee_managment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subtheme")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubTheme {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @OneToMany(mappedBy = "subTheme",cascade = CascadeType.ALL)
    private List<Question> questions;


}
