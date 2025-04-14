package com.telusko.question_service.controller;


import com.telusko.question_service.model.Question;
import com.telusko.question_service.model.QuestionWrapper;
import com.telusko.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")

public class QuestionController {
@Autowired
QuestionService questionService;
@GetMapping("/allQuestions")
    public  ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
}

@GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
    return  questionService.getQuestionByCategory(category);
}
@PostMapping("/add")
public ResponseEntity<String> addQuestion(@RequestBody Question question ){

    return new ResponseEntity<>(questionService.addQuestion(question).getStatusCode());

}
@DeleteMapping("/Delete/{id}")
public String deleteQuestion(@PathVariable int id){
    return questionService.deleteQuestion(id);
}


@GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
    return questionService.getQuestionsForQuiz(categoryName,numQuestions);
}
@PostMapping("/getQuestions")

    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer>questionIds)
{
    return questionService.getQuestionsFromId(questionIds);

}

}

