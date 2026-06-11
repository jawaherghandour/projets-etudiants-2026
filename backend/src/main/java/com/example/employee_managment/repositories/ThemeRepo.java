package com.example.employee_managment.repositories;

import com.example.employee_managment.entities.Theme;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
@Repository
public interface ThemeRepo extends JpaRepository<Theme, UUID> {
}
