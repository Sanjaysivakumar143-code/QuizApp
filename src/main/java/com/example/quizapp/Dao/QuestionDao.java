package com.example.quizapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quizapp.Model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    List<Question> findByDifficultyLevel(String difficultyLevel);

    @Query(value="SELECT * FROM question q where q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery= true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
