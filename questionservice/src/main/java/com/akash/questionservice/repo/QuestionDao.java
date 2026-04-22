package com.akash.questionservice.repo;

import com.akash.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    @Query("SELECT q FROM Question q WHERE LOWER(q.category) = LOWER(:category)")
    List<Question> findByCategory(@Param("category") String category);

    @Query("SELECT q FROM Question q WHERE LOWER(q.category) = LOWER(:category)")
    List<Question> getQuestionsByCategory(String category);




    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);
}

