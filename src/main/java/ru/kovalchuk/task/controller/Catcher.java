package ru.kovalchuk.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kovalchuk.common.Exception.NoEntityException;
import ru.kovalchuk.task.model.ValidationError;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class Catcher {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationError> handleException(ConstraintViolationException e) {
        /** ConstraintViolationException если не прошла валиадция самого значения метода **/
        ValidationError response = new ValidationError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationError> paramsException(MethodArgumentNotValidException e) {
        /** MethodArgumentNotValidException если не прошла валиадция параметра в значении метода **/
        StringBuilder sb = (new StringBuilder(""));
        BindingResult bindingResult = e.getBindingResult();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                sb.append("field [").append(fieldError.getField()).append("] ");
                sb.append("error [").append(fieldError.getDefaultMessage()).append("] ");
            }
        }
        ValidationError response = new ValidationError(sb.toString());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({NoEntityException.class})
    public ResponseEntity<ValidationError> noEntityException(NoEntityException e) {
        /** noEntityException если не найдены данные **/
        ValidationError response = new ValidationError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
