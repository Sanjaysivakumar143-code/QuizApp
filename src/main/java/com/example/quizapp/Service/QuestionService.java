package com.example.quizapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizapp.Dao.QuestionDao;
import com.example.quizapp.Model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }
    public List<Question> getQuestionsByCategory(String category){
        return questionDao.findByCategory(category);
    }
    public void addQuestions(Question question){
        questionDao.save(question);
    }
    public List<Question> getQuestionsByDifficultyLevel(String difficultyLevel) {
        return questionDao.findByDifficultyLevel(difficultyLevel);
    }
}
