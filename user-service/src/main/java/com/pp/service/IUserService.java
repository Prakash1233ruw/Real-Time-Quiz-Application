package com.pp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pp.model.QuizDto;
import com.pp.model.Response;
import com.pp.model.User;

public interface IUserService {

	ResponseEntity<String> registerUser(User user);

	ResponseEntity<?> getQuizData(Integer id);



	ResponseEntity<String> generateQuiz(QuizDto quizDto);

	ResponseEntity<?> calculateResult(Integer id, List<Response> response);

}
