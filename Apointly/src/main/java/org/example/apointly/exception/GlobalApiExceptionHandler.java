package org.example.apointly.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import org.example.apointly.dto.error.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    // Handler for our specific business exceptions (4xx errors)
    @ExceptionHandler({ResourceNotFoundException.class, EmailAlreadyExistsException.class})
    public ResponseEntity<ApiErrorResponse> handleBusinessExceptions(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status;
        if (ex instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND; // 404
        } else { // EmailAlreadyExistsException
            status = HttpStatus.CONFLICT; // 409
        }

        // Log client errors as warnings, as they are not server faults.
        logger.warn("HttpStatus: {} at path {}: {}", status.getReasonPhrase(), request.getRequestURI(), ex.getMessage());

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, status);
    }


    // Handler for validation errors (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            if (!validationErrors.containsKey(fieldName)) {
                validationErrors.put(fieldName, errorMessage);
            }
        });

        logger.warn("Validation failed for path {}: {}", request.getRequestURI(), validationErrors);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed", // A generic message
                request.getRequestURI(),
                validationErrors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Catch-all handler for unexpected server errors (500 errors)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        // Log server errors with a full stack trace. THIS is where you'd trigger a Slack notification!
        logger.error("Unexpected error occurred at path {}:", request.getRequestURI(), ex);

        // --- FUTURE SLACK NOTIFICATION HOOK ---
        // if (notificationService != null) {
        //     notificationService.sendAlert("Critical Error: " + ex.getMessage());
        // }

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                status.value(),
                "An unexpected internal server error has occurred.", // Don't leak details to the client
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
