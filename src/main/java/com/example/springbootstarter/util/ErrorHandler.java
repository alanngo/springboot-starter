package com.example.springbootstarter.util;

import com.example.springbootstarter.exception.InvalidPayloadException;
import com.example.springbootstarter.helper.JsonObject;
import com.example.springbootstarter.helper.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralError(Exception e) {
        JsonObject error = new JsonObject(
                Pair.create("message", e.getMessage()),
                Pair.create("code", INTERNAL_SERVER_ERROR.value())
        );
        log.error("General error: ", e);
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<?> handleInvalidPayload(InvalidPayloadException e) {
        JsonObject error = new JsonObject(
                Pair.create("message", "Invalid payload"),
                Pair.create("violations", e.getMessages()),
                Pair.create("code", BAD_REQUEST.value())
        );
        log.error("Invalid payload exception: {}", e.getMessages());
        return ResponseEntity.badRequest().body(error);
    }
}
