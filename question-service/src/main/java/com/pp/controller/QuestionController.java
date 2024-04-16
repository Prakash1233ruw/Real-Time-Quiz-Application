package com.pp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pp.model.Question;
import com.pp.model.QuestionWrapper;
import com.pp.model.Response;
import com.pp.service.QuestionService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("question")
public class QuestionController {
  @Autowired
	QuestionService questionService;
  @Autowired
    Environment environment;
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
	return 	questionService.addQuestion(question);
	}
	@DeleteMapping("/del/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
	return	questionService.deleteQuestionById(id);
	}
	
	//update question
	@PutMapping("update/{id}")
	public String updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
		
		
		return questionService.updateQuestionWithId(id,question);
	}
	
	//generate 
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,@RequestParam Integer noq){
	  return questionService.getQuestionsForQuiz(category,noq);
	}
	
	//getQuestions(question id)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
		return	questionService.getQuestionsfromId(questionIds);
	}
	
	//get Score
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.calculateScore(responses);
	}
}
