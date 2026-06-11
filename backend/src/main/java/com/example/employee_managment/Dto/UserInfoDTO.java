package com.example.employee_managment.Dto;

import java.util.UUID;

public record UserInfoDTO(UUID id,
                          String firstName,
                          String lastName,
                          String email,
                          String username,
                          String position,
                          String specialityName) {
}
