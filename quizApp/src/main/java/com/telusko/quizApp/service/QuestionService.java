package com.telusko.quizApp.service;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questiondao;
    private static final Logger LOGGER = Logger.getLogger(QuestionService.class.getName());

    public ResponseEntity<List<Question> >getAllQuestions(){
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }
        catch(Exception e ){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }
        catch(Exception e ){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<String >addQuestion(Question question) {
        try {
            questiondao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>("Failed to add question",HttpStatus.BAD_REQUEST);
    }

    public String deleteQuestion(int id) {
        questiondao.deleteById(id);
        return "Question deleted successfully!";
    }
}
