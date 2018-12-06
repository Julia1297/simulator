package application.controllers;


import application.exception.UserNotFoundException;
import application.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Error> usernameAlreadyExistsExceptionHandler(){
        return new ResponseEntity<>(new Error("Validation failed: Username has already been taken"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFoundException(){
        return new ResponseEntity<>(new Error("User not found") , HttpStatus.NOT_FOUND);
    }


}
