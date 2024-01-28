package br.com.fiap.techFlix.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handleIllegalArgument(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().getDetail());
    }

    @Test
    void shouldReturnPayloadTooLargeWhenMaxUploadSizeExceededExceptionIsThrown() {
        MaxUploadSizeExceededException exception = new MaxUploadSizeExceededException(1L);
        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handleMaxUploadSizeExceededException(exception);
        assertEquals(HttpStatus.PAYLOAD_TOO_LARGE, response.getStatusCode());
    }

    @Test
    void shouldReturnInternalServerErrorWhenExceptionIsThrown() {
        Exception exception = new Exception("Server error");
        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handleException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Server error", response.getBody().getDetail());
    }

}