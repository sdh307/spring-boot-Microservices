package com.akash.quizservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {

    String categoryName;
    Integer numQuestions;
    String title;


}
