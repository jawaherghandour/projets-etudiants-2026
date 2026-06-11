package com.example.employee_managment.abstrac;

import com.example.employee_managment.Dto.AnswerCreate;
import com.example.employee_managment.entities.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    Answer createAnswer(AnswerCreate answerCreate);


    List<Answer> getAnswerByQuestionId(UUID questionId);
}
