package com.example.employee_managment.contollers;

import com.example.employee_managment.Dto.QuestionCreate;
import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.abstrac.QuestionService;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @PostMapping
    public ResponseEntity<Question> Create (@RequestBody QuestionCreate questionCreate){
       Question  newquestion =questionService.createQuestion(questionCreate);
        return new ResponseEntity<Question>(newquestion, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Question>>> getAll(){
       List<Question>  questions =questionService.getAllQuestion();
       return new ResponseEntity<>(new GlobalResponse<>(questions), HttpStatus.OK);
    }
    @GetMapping("/{questionId}")
    public Question getById (@PathVariable UUID questionId){
        return questionService.getQuestionById(questionId);
    }



    @GetMapping("/by-subtheme/{subthemeId}")
    public ResponseEntity<GlobalResponse<List<QuestionCreate>>> getQuestionsBySubTheme(@PathVariable UUID subthemeId) {
        List<Question> questions = questionService.getQuestionsBySubThemeId(subthemeId);
        List<QuestionCreate> dtos = questions.stream()
                .map(QuestionCreate::fromQuestion)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new GlobalResponse<>(dtos), HttpStatus.OK);
    }
}
