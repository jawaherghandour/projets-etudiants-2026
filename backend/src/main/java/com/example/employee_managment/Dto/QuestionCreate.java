package com.example.employee_managment.Dto;

import com.example.employee_managment.entities.Question;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record QuestionCreate(
                            // String content,
                             //UUID employeeId,
                             //UUID subthemeId
                            UUID id,
                            String content,
                            LocalDateTime createdAt,
                            UUID employeeId,
                            String employeeFirstName,
                            String employeeLastName,
                            UUID subThemeId,
                            String subThemeName) {
    public static QuestionCreate fromQuestion(Question question) {
        return new QuestionCreate(
                question.getId(),
                question.getContent(),
                question.getCreatedAt(),
                question.getEmployee().getId(),
                question.getEmployee().getFirstName(),
                question.getEmployee().getLastName(),
                question.getSubTheme().getId(),
                question.getSubTheme().getName()
        );
    }
}
