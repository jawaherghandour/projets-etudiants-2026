package com.example.employee_managment.repositories;

import com.example.employee_managment.entities.SubTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SubThemeRepo extends JpaRepository<SubTheme, UUID> {





}
