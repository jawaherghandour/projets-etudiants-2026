package com.example.employee_managment.Dto;

public record ResetPasswordRequest(String token, String newPassword) {
}
