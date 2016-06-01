package com.stayhungry.primer.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * This class demonstrates how we intercept exceptions to avoid generic out of the box Spring error responses.
 * 
 * @author dcarrillo
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<String> genericExceptionHandler(HttpServletRequest request, Exception e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		JSONObject error = new JSONObject();
		try {
			error.put("error", e.getMessage());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return new ResponseEntity<String>(error.toString(), httpStatus);		
	}
}