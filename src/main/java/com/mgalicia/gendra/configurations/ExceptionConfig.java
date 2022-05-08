package com.mgalicia.gendra.configurations;

import com.mgalicia.gendra.helpers.DataHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> internalServerErrorException(Exception e) {

        return new ResponseEntity<Map<String, Object>>(response(e), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> response(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put(DataHelper.MESSAGE, e.getMessage());
        response.put(DataHelper.EXCEPTION, e.getClass().getSimpleName());
        return response;
    }
}
