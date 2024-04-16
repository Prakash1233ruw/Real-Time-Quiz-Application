package com.pp.dao;

import java.util.List;

import org.aspectj.weaver.tools.Trace;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.model.Question;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,Integer> {

	List<Question> findByCategory(String category);

    
	
    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :noq", nativeQuery = true)
	List<Integer> findRandomQuestionByCategory(String category, Integer noq);

    
}
