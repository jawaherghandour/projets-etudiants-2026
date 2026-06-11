package com.example.employee_managment.abstrac;

import com.example.employee_managment.Dto.QuestionCreate;
import com.example.employee_managment.entities.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question createQuestion(QuestionCreate questionCreate);
    List<Question> getAllQuestion();
    Question getQuestionById(UUID questionId);
    List<Question> getQuestionsBySubThemeId(UUID subthemeId);
}
