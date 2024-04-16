package com.pp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pp.feign.QuizInterface;
import com.pp.model.QuestionWrapper;
import com.pp.model.Quiz;
import com.pp.model.QuizDto;
import com.pp.model.Response;
import com.pp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizzController {
	@Autowired
	QuizService quizService;
	
	
	
    @PostMapping("/create")
	public ResponseEntity<String> createQuizz(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
	}
    
    @PostMapping("/start/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Integer id){
    		 return quizService.getQuizData(id);
    }
    
    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
    return	quizService.calculateResult(id,response);
    	
    }
}
