package br.com.fiap.techFlix.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private MessageSource messageSource;
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        messageSource = mock(MessageSource.class);
        globalExceptionHandler = new GlobalExceptionHandler(messageSource);
    }

    @Test
    void shouldReturnBadRequestWhenValidationErrorIsThrown() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getLocalizedMessage()).thenReturn("Validation error");
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        List<FieldError> fieldErrors = Collections.singletonList(fieldError);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())).thenReturn("Validation error message");

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.validationError(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation error", response.getBody().getTitle());
        assertEquals("One or more fields have incorrect data or the data already exists", response.getBody().getDetail());
        assertEquals(1, ((List<?>) response.getBody().getProperties().get("invalidParams")).size());
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