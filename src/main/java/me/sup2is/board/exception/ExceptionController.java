package me.sup2is.board.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<Object> formValidation(MethodArgumentNotValidException e) {
		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		List<String> errorMessages = new ArrayList<>();
		
		for (ObjectError objectError : errors) {
			errorMessages.add(objectError.getDefaultMessage());
		}
		
		return new ResponseEntity<Object>(errorMessages , HttpStatus.BAD_REQUEST);
	}
	
}
