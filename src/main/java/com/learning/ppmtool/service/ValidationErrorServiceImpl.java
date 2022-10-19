package com.learning.ppmtool.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {

	@Override
	public ResponseEntity<Map<String, String>> getAllValidationErrors(BindingResult result) {
		
		if (result.hasErrors()) {
			Map<String,String> fieldVsErroMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) 
				fieldVsErroMap.put(error.getField(), error.getDefaultMessage());
			
			return new ResponseEntity<Map<String,String>>(fieldVsErroMap, HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}

}
