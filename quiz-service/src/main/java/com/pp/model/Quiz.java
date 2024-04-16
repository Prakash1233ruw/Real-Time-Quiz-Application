package com.pp.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
private String title;
@ElementCollection
private List<Integer> questions;
public Quiz() {
	super();
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public List<Integer> getQuestions() {
	return questions;
}
public void setQuestions(List<Integer> questions) {
	this.questions = questions;
}
}
