package micro.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ApiError> handleException(DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("DataIntegrityViolationException thrown", e.getCause());
        final String message = Optional.ofNullable(e.getCause()).map(Throwable::getCause).map(Throwable::getMessage).orElse(e.getMessage());
        final ApiError apiError = new ApiError(request.getRequestURI(), message, HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleException(EntityNotFoundException e, HttpServletRequest request) {
        log.error("EntityNotFoundException thrown", e.getCause());
        final ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    public ResponseEntity<ApiError> handleException(DuplicatedEntityException e, HttpServletRequest request) {
        log.error("DuplicatedEntityException thrown", e.getCause());
        final ApiError apiError = new ApiError(
            request.getRequestURI(),
            e.getMessage(),
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleException(BadRequestException e, HttpServletRequest request) {
        log.error("BadRequestException thrown", e.getCause());
        final ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException thrown", e.getCause());
        final ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception thrown", e.getCause());
        final ApiError apiError = new ApiError(
            request.getRequestURI(),
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
