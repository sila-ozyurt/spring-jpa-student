package com.hediyesilaozyurt.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private <T> ApiError<T> createApiError(HttpStatus status, ErrorCode errorCode,HttpServletRequest request, T error) {
        ErrorDetailForApi<T> errorDetailForApi=createException(errorCode,request,error);
        return new ApiError<>(status.value(),errorDetailForApi);
    }

    //to create apierror we need errordetailforapi object
    private <T> ErrorDetailForApi<T> createException( ErrorCode errorCode,HttpServletRequest request, T error){
        return new ErrorDetailForApi<>(
                LocalDateTime.now(),
                getHostName(),
                errorCode,
                request.getRequestURI(),
                error);
    }

    //to create errordetailforapi we need hostname
    private String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException ex){
            return "unknown-host";
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        log.warn("Validation error: {}", ex.getMessage());

        Map<String, List<String>> errorMap = new HashMap<>();

        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createApiError(HttpStatus.BAD_REQUEST,ErrorCode.VALIDATION_FAILED,request,errorMap));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.error("Database constraint: {}", ex.getMessage());

        String message = "Database constraint occured";
        if (ex.getMessage() != null) {
            if (ex.getMessage().toLowerCase().contains("foreign key")) {
                message = "cannot delete because it is referenced by other records";
            } else if (ex.getMessage().toLowerCase().contains("unique")) {
                message = "a record with this value already exists";
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(createApiError(HttpStatus.CONFLICT,ErrorCode.DATABASE_CONSTRAINT,request,message));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError<String>> handleEntityNotFoundError(EntityNotFoundException ex, HttpServletRequest request) {
        log.error("Entity not found: {}", ex.getMessage());
        ApiError<String> apiError = createApiError(HttpStatus.NOT_FOUND, ErrorCode.ENTITY_NOT_FOUND,request,"entity not found error occured");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError<String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.error("Argument type mismatch: {}", ex.getMessage());
        String message;
        if(ex.getRequiredType()!=null) {
            message=String.format("Invalid value '%s' for parameter '%s'. Expected type: '%s'", ex.getValue(), ex.getName(),ex.getRequiredType().getSimpleName());
        }
        else{
            message="Argument type mismatch occured";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createApiError(HttpStatus.BAD_REQUEST,ErrorCode.INVALID_ARGUMENT_TYPE,request,message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<String>> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error occrued: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createApiError(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCode.INTERNAL_SERVER_ERROR,request,"something unexpected went wrong."));
    }


}