package com.book.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.book.servelet.BookRegistrationServlet.*(..))")
    public void logBefore() {
        System.out.println("Logging before method execution.");
    }

    @After("execution(* com.book.servelet.BookRegistrationServlet.*(..))")
    public void logAfter() {
        System.out.println("Logging after method execution.");
    }

    @AfterReturning("execution(* com.book.servelet.BookRegistrationServlet.*(..))")
    public void logAfterReturning() {
        System.out.println("Logging after method returns successfully.");
    }
    @AfterThrowing("execution(* com.book.servelet.BookRegistrationServlet.*(..))")
    public void logAfterThrowing() {
        System.out.println("Logging after method throws an exception.");
    }
}
