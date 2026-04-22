package com.akash.questionservice.controller;


import com.akash.questionservice.model.Question;
import com.akash.questionservice.model.QuestionWrapper;
import com.akash.questionservice.model.Response;
import com.akash.questionservice.service.QuestionService;
import org.springframework.core.env.Environment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllStudents(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);

    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion( @RequestBody Question question){
        return questionService.addQuestion(question);

    }


    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQ){

        return questionService.getQuestionForQuiz(categoryName,numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {

        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses){
        return questionService.getScore(responses);
    }
}
