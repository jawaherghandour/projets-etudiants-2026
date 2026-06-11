package com.example.employee_managment.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SignupRequest(
        @NotNull(message="username is required")
        @Size(min=2 , max = 100, message="min is 2 character and max is 100 character")
        String username,
        @NotNull(message="password is required")
        @Size(min=2 , max = 100, message="min is 2 character and max is 100 character")
        String password,
        @NotNull(message="employee id is required")
        UUID employeeId
        ) {

}
