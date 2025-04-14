package com.telusko.quizApp.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    public QuestionWrapper(Integer id, String option4, String option3, String option2, String option1, String questionTitle) {
        this.id = id;
        this.option4 = option4;
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.questionTitle = questionTitle;
    }
    public Integer getId() { return id; }
    public String getQuestionTitle() { return questionTitle; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public String getOption3() { return option3; }
    public String getOption4() { return option4; }
}
