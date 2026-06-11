package com.example.employee_managment.Service;

import com.example.employee_managment.Dto.AnswerCreate;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.abstrac.AnswerService;
import com.example.employee_managment.entities.Answer;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.Question;
import com.example.employee_managment.repositories.AnswerRepo;
import com.example.employee_managment.repositories.EmployeeRepo;
import com.example.employee_managment.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AnswerServiceImp implements AnswerService {
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public Answer createAnswer(AnswerCreate answerCreate) {
        Answer answer1 = new Answer();
        Optional<Question> question = questionRepo.findById(answerCreate.questionId());
        if (question.isEmpty()) throw CustomResponseException.ResourceNotFound("Question not found");
       Optional<Employee> employee = employeeRepo.findById(answerCreate.employeeId());
        if (employee.isEmpty()) throw CustomResponseException.ResourceNotFound("employee not found");
        answer1.setContent(answerCreate.content());
        answer1.setCreatedAt(LocalDateTime.now());
        answer1.setEmployee(employee.get());
        answer1.setQuestion(question.get());
        answerRepo.save(answer1);



        return answer1;
    }




    @Override
    public List<Answer>getAnswerByQuestionId(UUID questionId) {
        return answerRepo.findByQuestionId(questionId);
    }


}
