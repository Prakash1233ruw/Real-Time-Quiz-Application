package com.pp.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pp.model.QuestionWrapper;
import com.pp.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	//generate 
		@GetMapping("/question/generate")
		public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,@RequestParam Integer noq);
		
		//getQuestions(question id)
		@PostMapping("/question/getQuestions")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
		
		//get Score
		
		@PostMapping("/question/getScore")
		public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
