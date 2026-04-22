package com.akash.quizservice.service;


import com.akash.quizservice.feign.QuizInterface;
import com.akash.quizservice.model.QuestionWrapper;
import com.akash.quizservice.model.Quiz;
import com.akash.quizservice.model.Response;
import com.akash.quizservice.repo.QuizDao;
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
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;



    /*
    @Autowired
    QuestionDao questionDao;  */

    // ✅ Create a quiz with random questions
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);




       /*
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        */
        return ResponseEntity.ok("Quiz created successfully!");
    }

    // ✅ Get quiz questions (without correct answers)
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds= quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;

       /* Optional<Quiz> quiz = quizDao.findById(id);

        if (quiz.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<Question> questionFromDB = quiz.get().getQuestions();
        */




        /*
        for (Question q : questionFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionsForUser.add(questionWrapper);
        }*/


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;

       /* if (quiz.isEmpty()) {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }

        List<Question> questions = quiz.get().getQuestions(); */



        /*
        for (Response r : responses) {
            // find the question in quiz that matches the response id
            for (Question q : questions) {
                if (q.getId().equals(r.getId())) {
                    if (q.getRightAnswer().equalsIgnoreCase(r.getResponse())) {
                        right++;
                    }
                    break; // stop searching once matched
                }
            }
        }*/


    }

}
