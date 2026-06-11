package com.example.employee_managment.Service;

import com.example.employee_managment.Dto.QuestionCreate;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.abstrac.QuestionService;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.Question;
import com.example.employee_managment.entities.SubTheme;
import com.example.employee_managment.repositories.EmployeeRepo;
import com.example.employee_managment.repositories.QuestionRepo;
import com.example.employee_managment.repositories.SubThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private SubThemeRepo subThemeRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public Question createQuestion(QuestionCreate questionCreate) {
        Question question1 = new Question();
        SubTheme subTheme = subThemeRepo.findById(questionCreate.subThemeId())
                .orElseThrow(() -> new RuntimeException("SubTheme not found"));

        Optional<Employee>  employee = employeeRepo.findById(questionCreate.employeeId());
        if (employee.isEmpty()) throw CustomResponseException.ResourceNotFound("employee not found");


        question1.setContent(questionCreate.content());
        question1.setCreatedAt(LocalDateTime.now());
        question1.setEmployee(employee.get());
        question1.setSubTheme(subTheme);
        questionRepo.save(question1);
        return question1;
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    @Override
    public Question getQuestionById(UUID questionId) {
        return questionRepo.findById(questionId)
                .orElseThrow(()->new RuntimeException(("Question not found")));
    }

    @Override
    public List<Question> getQuestionsBySubThemeId(UUID subthemeId) {
        return questionRepo.findBySubThemeId(subthemeId);
    }

}
