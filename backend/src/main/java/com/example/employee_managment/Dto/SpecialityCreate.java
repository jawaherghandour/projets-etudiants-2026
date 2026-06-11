package com.example.employee_managment.Dto;

import jakarta.validation.constraints.NotNull;

public record SpecialityCreate(@NotNull(message = "name is required")
                               String name) {
}
