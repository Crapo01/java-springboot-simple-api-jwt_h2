package com.example.demo.exception;

import com.example.demo.model.Response;
import com.example.demo.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<Response> handleCustomerNotFound(CustomerNotFoundException ex) {
    Response response = ResponseUtil.error(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getAllErrors().stream()
            .map(error -> ((FieldError) error).getDefaultMessage())
            .collect(Collectors.toList());

    String errorMessage = String.join(", ", errors);
    Response response = ResponseUtil.error("Validation failed: " + errorMessage);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response> handleGlobalException(Exception ex) {


    String errorMessage = ex.getMessage();
    Response response = ResponseUtil.error("An unexpected error occurred"+ errorMessage);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
