package ru.kovalchuk.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kovalchuk.task.model.ValidationError;

import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@ControllerAdvice
public class Catcher {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationError> handleException(ConstraintViolationException e) {
        ValidationError response = new ValidationError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationError> paramsException(MethodArgumentNotValidException e) {
        StringBuilder sb = (new StringBuilder(""));
        BindingResult bindingResult = e.getBindingResult();
        Iterator var = bindingResult.getAllErrors().iterator();
        while (var.hasNext()){
            ObjectError error = (ObjectError)var.next();
            if (error instanceof FieldError){
                FieldError fieldError = (FieldError) error;
                sb.append("field [").append(fieldError.getField()).append("] ");
                sb.append("error [").append(fieldError.getDefaultMessage()).append("] ");
            }
        }
        ValidationError response = new ValidationError(sb.toString());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }
}
