 package com.pp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.pp.dao.QuizDao;
import com.pp.feign.QuizInterface;
import com.pp.model.QuestionWrapper;
import com.pp.model.Quiz;
import com.pp.model.Response;

@Service
public class QuizService {
    @Autowired
	QuizDao quizDao;

//    @Autowired
//	QuestionDao questionDao;

    
    @Autowired
	QuizInterface quizInterface;
    
	public ResponseEntity<String> createQuiz(String category, Integer noq, String title) {
		
		List<Integer> questions  =quizInterface.generateQuiz(category, noq).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setQuestions(questions);
		quiz.setTitle(title);
		quizDao.save(quiz);
		
		
		return new ResponseEntity<String>("Success",HttpStatus.CREATED) ;
		
	}

	public ResponseEntity<?> getQuizData(Integer id) {
		
		Optional<Quiz> optional = quizDao.findById(id);
		 
		     
		if(optional.isPresent()) {
		  List<Integer> questionsFromDb = optional.get().getQuestions();
		 
		 List<QuestionWrapper> questionsForUsers=quizInterface.getQuestionsFromId(questionsFromDb).getBody();
//		 for(Integer q:questionsFromDb) {
//			QuestionWrapper questionWrapper = new QuestionWrapper(q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//		     questionsForUsers.add(questionWrapper);
//		 }
		    return new ResponseEntity<List<QuestionWrapper>>(questionsForUsers,HttpStatus.OK); 

		}else {
			return new ResponseEntity<String>("Quiz not found",HttpStatus.BAD_GATEWAY);
		}
		
	}

	public ResponseEntity<?> calculateResult(Integer id, List<Response> response) {
     
		Optional<Quiz> optional = quizDao.findById(id);
		if(optional.isPresent()) {
			
			
			Integer right = quizInterface.getScore(response).getBody();
			                  
			
		    return new ResponseEntity<Integer>(right,HttpStatus.ACCEPTED); 

		}
		
	    return new ResponseEntity<String>("unable to submit the quizz ",HttpStatus.BAD_REQUEST); 
	}
    
}
