package com.example.employee_managment.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeUpdate(@NotNull(message = "firstName is required")
                             String firstName,
                             @NotNull(message = "lastName is required")
                             String lastName,

                             @NotNull(message = "Phone Number is required")
                             String phoneNumber,
                             @NotNull(message = "hiredate is required")
                             LocalDate hireDate,
                             @NotNull(message = "position is required")
                             String position) {
}
