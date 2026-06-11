package com.example.employee_managment.Dto;

import java.util.UUID;

public record AnswerCreate(
        String content,
        UUID questionId ,
        UUID employeeId
) {
}
