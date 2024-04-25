package com.pp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.model.QuizDto;
import com.pp.model.Response;
import com.pp.model.User;
import com.pp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> userRegistration(@RequestBody User user){
		return userService.registerUser(user);
	}
	
    @PostMapping("/generate")
	public ResponseEntity<String> createQuizz(@RequestBody QuizDto quizDto){
		return userService.generateQuiz(quizDto);
	}
    
    @PostMapping("/start/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Integer id){
    		 return userService.getQuizData(id);
    }
    
    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
    return	userService.calculateResult(id,response);
    	
    }
}
