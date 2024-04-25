package com.pp.model;

public class QuizDto {
private String categoryName;
private Integer numQuestions;
private String title;
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public Integer getNumQuestions() {
	return numQuestions;
}
public QuizDto(String categoryName, Integer numQuestions, String title) {
	super();
	this.categoryName = categoryName;
	this.numQuestions = numQuestions;
	this.title = title;
}
public void setNumQuestions(Integer numQuestions) {
	this.numQuestions = numQuestions;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}
