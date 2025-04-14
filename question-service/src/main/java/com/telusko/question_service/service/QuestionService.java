package com.telusko.question_service.service;


import com.telusko.question_service.dao.QuestionDAO;
import com.telusko.question_service.model.Question;
import com.telusko.question_service.model.QuestionWrapper;
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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName,Integer numQuestions) {
        List<Integer> questions=questiondao.findRandomQuestionByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
    List<QuestionWrapper>wrappers=new ArrayList<>();
    List<Question>questions=new ArrayList<>();
    for(Integer id:questionIds){
        questions.add(questiondao.findById(id).get());

    }
    for(Question question:questions){
        QuestionWrapper wrapper=new QuestionWrapper();
    }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

}
