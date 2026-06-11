package com.example.employee_managment.repositories;

import com.example.employee_managment.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AnswerRepo extends JpaRepository<Answer, UUID> {
    List<Answer> findByQuestionId(UUID questionId);

}
