package com.example.quizapp.Model;

import lombok.Data;

@Data
public class QuestionWrapper {

    
    private Integer id;
    private String questionTitle;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    public QuestionWrapper(Integer id, String questionTitle, String optionA, String optionB, String optionC,
            String optionD) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    
}
