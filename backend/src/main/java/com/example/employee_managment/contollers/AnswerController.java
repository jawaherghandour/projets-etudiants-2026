package com.example.employee_managment.contollers;

import com.example.employee_managment.Dto.AnswerCreate;
import com.example.employee_managment.Dto.UserInfoDTO;
import com.example.employee_managment.Shared.CustomResponseException;
import com.example.employee_managment.Shared.GlobalResponse;
import com.example.employee_managment.abstrac.AnswerService;
import com.example.employee_managment.entities.Answer;
import com.example.employee_managment.entities.Employee;
import com.example.employee_managment.entities.UserAccount;
import com.example.employee_managment.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> Create(@RequestBody AnswerCreate answerCreate){
         Answer newanswer=answerService.createAnswer(answerCreate);
         return  new ResponseEntity<Answer>(newanswer, HttpStatus.CREATED);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<GlobalResponse< List<Answer>>>getAllAnswerByQuestionId(@PathVariable UUID questionId){
       List<Answer> answer =answerService.getAnswerByQuestionId(questionId);
        return  new ResponseEntity<>(new GlobalResponse<>(answer), HttpStatus.OK);
    }





}
