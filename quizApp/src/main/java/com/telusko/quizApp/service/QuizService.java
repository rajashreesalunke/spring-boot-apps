package com.telusko.quizApp.service;

import com.telusko.quizApp.dao.QuestionDAO;
import com.telusko.quizApp.dao.QuizDao;
import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.model.QuestionWrapper;
import com.telusko.quizApp.model.Quiz;
import com.telusko.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
QuizDao quizdao;
    @Autowired
    QuestionDAO questiondao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questiondao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

//We're fetching a quiz by its ID, retrieving its list of questions, converting them into a simplified QuestionWrapper format, and returning that list as a response. 🚀
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
   Optional<Quiz> quiz=quizdao.findById(id);
    List<Question>questionsFromDB=quiz.get().getQuestions();
    List<QuestionWrapper>questionForUser=new ArrayList<>();
    for(Question q: questionsFromDB){

        QuestionWrapper qw=new  QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
        questionForUser.add(qw);
    }

    return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizdao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

        i++;
    }
        return  new ResponseEntity<>(right,HttpStatus.OK);
    }
}
