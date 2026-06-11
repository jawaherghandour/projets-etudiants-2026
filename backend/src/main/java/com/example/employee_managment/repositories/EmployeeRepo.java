package com.example.employee_managment.repositories;

import com.example.employee_managment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
}
