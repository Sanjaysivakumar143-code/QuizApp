package com.example.quizapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import com.example.quizapp.Dao.QuestionDao;
import com.example.quizapp.Dao.QuizDao;
import com.example.quizapp.Model.Question;
import com.example.quizapp.Model.QuestionWrapper;
import com.example.quizapp.Model.Quiz;
import com.example.quizapp.Model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        System.out.println(questions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        // System.out.println(questionFromDB);
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q: questionFromDB) {
            questionsForUser.add(new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOptionA(),q.getOptionB(),q.getOptionC(),q.getOptionD()));
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i=0;
        for(Response response: responses) {
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
    



}
