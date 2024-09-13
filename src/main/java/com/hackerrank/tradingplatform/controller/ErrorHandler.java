package com.hackerrank.tradingplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hackerrank.tradingplatform.exception.UserAlreadyExitException;

@ControllerAdvice
public class ErrorHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExitException.class)
	public void userAlreadyExist() {
		
	}
}
