package com.pp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pp.advice.QuestionNotFoundException;
import com.pp.dao.QuestionDao;
import com.pp.model.Question;
import com.pp.model.QuestionWrapper;

import com.pp.model.Response;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
	    try {
	        List<Question> questions = questionDao.findAll();
	        return new ResponseEntity<>(questions, HttpStatus.OK);
	    } catch (Exception e) {
	        // Log the exception or handle it in some appropriate way
	        e.printStackTrace(); 
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}




	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
	    try {
	        List<Question> questions = questionDao.findByCategory(category);
	        return new ResponseEntity<>(questions, HttpStatus.OK);
	    } catch (Exception e) {
	        // Log the exception or handle it in some appropriate way
	        e.printStackTrace(); 
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	public ResponseEntity<String> addQuestion(Question question) {
	    try {
	        if (question == null) {
	            throw new IllegalArgumentException("Question cannot be null");
	        }
	        
	        questionDao.save(question);
	        return new ResponseEntity<>("Success", HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to add question: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}




	public ResponseEntity<String> deleteQuestionById(Integer id) {
	    return questionDao.findById(id)
	            .map(question -> {
	                questionDao.delete(question);
	                return ResponseEntity.status(HttpStatus.ACCEPTED)
	                        .body("Question with ID " + id + " deleted");
	            })
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Question with ID " + id + " not found"));
	}


    
	public String updateQuestionWithId(Integer id, Question question) {
	    if (id == null || question == null) {
	        throw new IllegalArgumentException("ID and Question must not be null");
	    }
	    
	    return questionDao.findById(id)
	            .map(existingQuestion -> {
	                existingQuestion.setQuestionTitle(question.getQuestionTitle());
	                existingQuestion.setOption1(question.getOption1());
	                existingQuestion.setOption2(question.getOption2());
	                existingQuestion.setOption3(question.getOption3());
	                existingQuestion.setOption4(question.getOption4());
	                existingQuestion.setRightAnswer(question.getRightAnswer());
	                existingQuestion.setCategory(question.getCategory());
	                existingQuestion.setDifficultyLevel(question.getDifficultyLevel());

	                questionDao.save(existingQuestion);
	                return "Question updated for ID " + id;
	            })
	            .orElseThrow(() -> new QuestionNotFoundException("Question Not Found with ID " + id));
	}




	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer noq) {
		
	List<Integer> listOfIds = questionDao.findRandomQuestionByCategory(category, noq);

     return new ResponseEntity<>(listOfIds,HttpStatus.OK);
	}




	public ResponseEntity<List<QuestionWrapper>> getQuestionsfromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();

		List<Question> questions=new ArrayList<>();
		for(Integer id:questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question q:questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			
			wrappers.add(wrapper);
		}
		return new ResponseEntity<List<QuestionWrapper>>(wrappers,HttpStatus.OK);
	}




	public ResponseEntity<Integer> calculateScore(List<Response> responses) {
		
			
			
			 int right=0;
			for(Response rsp:responses) {
				Question question = questionDao.findById(rsp.getId()).get();
				if(question.getRightAnswer().equals(rsp.getResponse())) {
					right++;
				}
			}
		    return new ResponseEntity<Integer>(right,HttpStatus.ACCEPTED); 

		
		
//	    return new ResponseEntity<Integer>("unable to submit the quizz ",HttpStatus.BAD_REQUEST); 
	}
    
}
