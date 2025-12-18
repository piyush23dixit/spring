package com.coding.PracticeSpring.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // First type
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
//        return  new ResponseEntity<>("Resource Not Found", HttpStatus.NOT_FOUND);
//
//    }

    //Second type
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException e){
//        ApiError apiError= ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
//        return  new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//
//    }

//    @ExceptionHandler(ResouceNotFoundException.class)
//    public ResponseEntity<ApiError> handleNoSuchElementException(ResouceNotFoundException e){
//        ApiError apiError= ApiError.builder()
//                .status(HttpStatus.NOT_FOUND)
//                .message(e.getMessage())
//                .build();
//        return  new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//
//    }

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoSuchElementException(ResouceNotFoundException e){
        ApiError apiError= ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return  buildErrorResponseEntity(apiError);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> HandleInternalServerError(Exception e){
        ApiError apiError= ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return  buildErrorResponseEntity(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException e){
       List<String> errors= e
               .getBindingResult()
               .getAllErrors()
               .stream()
               .map(error -> error.getDefaultMessage())
               .collect(Collectors.toList());
        ApiError apiError= ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation error")
                .subErrors(errors)
                .build();
        return  buildErrorResponseEntity(apiError);

    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
