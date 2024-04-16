package com.pp.advice;

import java.time.LocalDateTime;

public class ErrorDetails {
public String statusCode;
public String message;
public LocalDateTime when;
public ErrorDetails(String statusCode, String message, LocalDateTime when) {
	super();
	this.statusCode = statusCode;
	this.message = message;
	this.when = when;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LocalDateTime getWhen() {
	return when;
}
public void setWhem(LocalDateTime when) {
	this.when = when;
}


}
