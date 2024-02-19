package io.github.dolphin.software.users.controller.exceptions;

import io.github.dolphin.software.users.model.exceptions.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The ResponseEntityExceptionHandler class is a controller advice class that handles exceptions and provides error responses.
 *
 * <p>
 * This class defines a method called 'userNotFound' which is annotated with @ExceptionHandler and handles the UserNotFoundException.
 * When the UserNotFoundException is thrown, this method is invoked and it returns a ResponseEntity containing an error response.
 * The error response is in the form of a map, which contains the timestamp, message, and status code.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 *   @ExceptionHandler(UserNotFoundException.class)
 *   public ResponseEntity<Map<String, String>> userNotFound(@NotNull UserNotFoundException e) {
 *       // Handle the exception and return the error response
 *   }
 * }</pre>
 * </p>
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 * @see ResponseEntity
 * @see UserNotFoundException
 */
@RestControllerAdvice
public class ResponseEntityExceptionHandler {

    /**
     * Handles the UserNotFoundException and returns a ResponseEntity containing an error response.
     *
     * @param e The UserNotFoundException that needs to be handled.
     * @return The ResponseEntity containing the error response.
     *
     * @throws IllegalArgumentException if the e parameter is null.
     *
     * @see ResponseEntity
     * @see UserNotFoundException
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFound(@NotNull UserNotFoundException e) {

        Map<String, String> bodyResponse = new HashMap<>();
        bodyResponse.put("timestamp", String.valueOf(LocalDateTime.now()));
        bodyResponse.put("message", e.getMessage());
        bodyResponse.put("status", String.valueOf(HttpStatus.NOT_FOUND.toString()));


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse);
    }
}
