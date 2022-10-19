package com.learning.ppmtool.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ValidationErrorService {
	
	public ResponseEntity<Map<String,String>> getAllValidationErrors(BindingResult result);

}
