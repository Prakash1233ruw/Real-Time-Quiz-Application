package com.pp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pp.dao.UserDao;
import com.pp.feign.UserInterface;
import com.pp.model.QuestionWrapper;
import com.pp.model.QuizDto;
import com.pp.model.Response;
import com.pp.model.User;
import com.pp.model.UserPrinciple;
@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	 UserDao userDao;
	@Autowired
	UserInterface userInterface;
	@Override
	public ResponseEntity<String> registerUser(User user) {
		String name = userDao.save(user).getUserName();
		String message = name+" You are Successfully registered With US";
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	@Override
	 public ResponseEntity<?> getQuizData(Integer id) {
        ResponseEntity<?> response = null;
           try {
        	   response= userInterface.getQuiz(id);
        if (response.getStatusCode().value()==200) {
            // Successful response, further processing if needed
            response= userInterface.getQuiz(id);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            // Quiz not found
            response= new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
        } else {
            // Other error scenarios
            response= new ResponseEntity<>("Failed to fetch quiz data", response.getStatusCode());
        }
        }catch(Exception e){
        	response=new ResponseEntity<>("no question found with id "+id,HttpStatus.BAD_REQUEST);
        }
           return response;
    }
	@Override
	public ResponseEntity<String> generateQuiz(QuizDto quizDto) {
		 ResponseEntity<String> response =  userInterface.createQuizz(quizDto);
		 if(response.getStatusCode().value()==201) {
			 String data = response.getBody();
			 return new ResponseEntity<String>(data,HttpStatus.CREATED);
		 }else {
			 return new ResponseEntity<String>("not found",HttpStatus.CREATED);
		 }
			
		
	}
	@Override
	public ResponseEntity<?> calculateResult(Integer id, List<Response> response) {
Integer result = (Integer) userInterface.submitQuiz(id, response).getBody();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user = userDao.findByUserName(username);
		
		if(username==null) {
		System.out.println("404 not found");
	throw new  UsernameNotFoundException("User 404");
		}
		return new UserPrinciple(user);
	}
		
	

}
