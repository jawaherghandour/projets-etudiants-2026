package com.example.employee_managment.abstrac;


import com.example.employee_managment.Dto.EmployeeCreate;
import com.example.employee_managment.Dto.EmployeeUpdate;
import com.example.employee_managment.entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
Employee findOne(UUID employeeId);
List<Employee> findAll();
void deleteOne (UUID employeeId);
Employee UpdateOne(UUID employeeId, EmployeeUpdate employee);
Employee CreateOne(EmployeeCreate employee);
}

