package com.pp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
