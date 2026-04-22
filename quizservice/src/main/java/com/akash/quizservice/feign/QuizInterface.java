package com.akash.quizservice.feign;

import com.akash.quizservice.model.Question;
import com.akash.quizservice.model.QuestionWrapper;
import com.akash.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

    @GetMapping("question/allQuestions")
    ResponseEntity<List<Question>> getAllQuestions();

    @GetMapping("question/category/{category}")
    ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category);

    @PostMapping("question/add")
    ResponseEntity<String> addQuestion(@RequestBody Question question);

    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                      @RequestParam Integer numQ);

    @PostMapping("question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
