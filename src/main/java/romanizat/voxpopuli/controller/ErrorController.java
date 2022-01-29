package romanizat.voxpopuli.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import romanizat.voxpopuli.data.ResponseError;
import romanizat.voxpopuli.exception.UrlExistsException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
@Component
@RequiredArgsConstructor
public class ErrorController {

    @ExceptionHandler({
            NoSuchElementException.class,
            UsernameNotFoundException.class
    })
    protected ResponseEntity<ResponseError> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getResponseError(getErrorMessage(ex), request, status);
    }

    @ExceptionHandler({
            SQLIntegrityConstraintViolationException.class,
            DataIntegrityViolationException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    protected ResponseEntity<ResponseError> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        String message = ex.getMessage();
        if (ex instanceof DataIntegrityViolationException) {
            Throwable rootCause = ((DataIntegrityViolationException) ex).getRootCause();
            if (rootCause != null && rootCause.getMessage().contains("Cannot delete or update a parent row")) {
                message = "error.cannotDelete";
            }
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getResponseError(getErrorMessage(message), request, status);
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    protected ResponseEntity<ResponseError> handleForbidden(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return getResponseError(getErrorMessage(ex), request, status);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            Exception.class,
            UrlExistsException.class
    })
    protected ResponseEntity<ResponseError> handleInternalServerError(RuntimeException ex, HttpServletRequest request) {
        //ex.printStackTrace();
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            String message = responseStatus.reason();
            if (ex.getMessage() != null)
                message = String.format("%s: %s", responseStatus.reason(), ex.getMessage());

            return getResponseError(message, request, responseStatus.code());
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getResponseError(getErrorMessage(ex), request, status);
    }

    private String getErrorMessage(String messageProp) {
        if (messageProp == null)
            messageProp = "error.generic";

        return messageProp;
    }

    private ResponseEntity<ResponseError> getResponseError(String message, HttpServletRequest request, HttpStatus status) {
        ResponseError errorInfo = new ResponseError(status, request, message);
        return ResponseEntity.status(errorInfo.getCode()).body(errorInfo);
    }

    private String getErrorMessage(Exception exception) {
        return getErrorMessage(exception.getMessage());
    }
}
