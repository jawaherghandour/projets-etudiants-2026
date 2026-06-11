package com.example.employee_managment.repositories;

import com.example.employee_managment.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface QuestionRepo extends JpaRepository<Question, UUID> {

        List<Question> findBySubThemeId(UUID subthemeId);

}
