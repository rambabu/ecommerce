package com.acro.lab.ecommerce.advise;
import com.acro.lab.ecommerce.exception.EcommerceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Component
public class CommonExceptionHandler {
    private static final Logger LOGGER= LoggerFactory.getLogger(CommonExceptionHandler.class);
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> handleInvalidArgument(MethodArgumentNotValidException ex){

        Map<String,String> errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> errorMap.put(error.getField(),error.getDefaultMessage()));
        LOGGER.warn("Received BadRequest");
        return errorMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EcommerceException.class)
    public Map<String, String> handleMe(EcommerceException e) {
        LOGGER.error("Resource not found", e);  // logger
        return Collections.emptyMap();
    }


}
