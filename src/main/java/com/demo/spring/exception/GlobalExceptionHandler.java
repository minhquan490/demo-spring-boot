package com.demo.spring.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@AfterThrowing(pointcut = "execution(* com.demo.spring.repository..*.*(..))", throwing = "ex")
	public void hibernateExceptionHandler(HibernateException ex) {
		logger.error(ex.getMessage(), ex);
	}

	@AfterThrowing(pointcut = "execution(* com.demo.spring..*.*(..))", throwing = "e")
	public void exceptionHandler(Exception e) {
		logger.error(e.getMessage(), e);
	}
}