package com.example.employee_managment.contollers;

import com.example.employee_managment.Dto.EmployeeCreate;
import com.example.employee_managment.Dto.EmployeeUpdate;
import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.abstrac.EmployeeService;
import com.example.employee_managment.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees =employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findOne(@PathVariable UUID employeeId) {
        Employee employee =employeeService.findOne(employeeId);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> CreateOne(@RequestBody @Valid EmployeeCreate employee) {
       Employee Newemployee = employeeService.CreateOne(employee);
        return new ResponseEntity<Employee>(Newemployee, HttpStatus.CREATED);

    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> DeleteOne(@PathVariable UUID employeeId) {
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> UpdateOne(@PathVariable UUID employeeId, @RequestBody @Valid EmployeeUpdate employee) {
        Employee updatedEmployee = employeeService.UpdateOne(employeeId,employee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);

    }


}//class
