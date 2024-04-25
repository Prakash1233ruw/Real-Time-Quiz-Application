package com.pp.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pp.model.QuestionWrapper;
import com.pp.model.QuizDto;
import com.pp.model.Response;

@FeignClient("QUIZ-SERVICE")
public interface UserInterface {
	//generate 
	@PostMapping("/quiz/create")
	public ResponseEntity<String> createQuizz(@RequestBody QuizDto quizDto);
    
    @PostMapping("/quiz/start/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Integer id);
    
    
    @PostMapping("/quiz/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response);
    	
    
}
