package com.mpt.MadhyaPradeshTourism.exceptions;

import com.mpt.MadhyaPradeshTourism.response.Response;
import com.mpt.MadhyaPradeshTourism.util.ConstantMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


//@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
			@ExceptionHandler(MPTApplicationExceptions.class)
			public ResponseEntity<Object> handleFiveExOnlineExamExceptions(MPTApplicationExceptions ex) {
				log.error("FiveExOnlineExamExceptions : {}", ex.getLocalizedMessage());
				var errorString = String.join(",", Optional.ofNullable(ex.getErrors()).orElse(Collections.emptyList()));
				var message = StringUtils.contains(errorString, ex.getMessage()) ? ex.getMessage() : StringUtils.join(ex.getMessage(), errorString);
				var error = Response.failedResponse(ex.getHttpStatus().value(), message, ex.getData());
				return ResponseEntity.status(ex.getHttpStatus()).body(error);
			}


			@ExceptionHandler(Exception.class)
			public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
				log.error("Unexpected exception occurred : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage(), ex.getMessage());
				return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}


			@ExceptionHandler(MethodArgumentNotValidException.class)
			public ResponseEntity<Object> onConstraintValidationException(MethodArgumentNotValidException e) {
				log.error("MethodArgumentNotValidException : {}", e.getLocalizedMessage());
				 List<String> errors = e.getBindingResult().getFieldErrors()
			                .stream().map(FieldError::getDefaultMessage).toList();
				var error =  Response.failedResponse(HttpStatus.BAD_REQUEST.value(), errors.get(0), List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}


			@ResponseBody
			@ExceptionHandler(MissingRequestHeaderException.class)
			@ResponseStatus(HttpStatus.BAD_REQUEST)
			public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
				List<String> detailsList = new ArrayList<>();
				detailsList.add(ex.getLocalizedMessage());
				log.error("MissingRequestHeaderException Exception occurs : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}

			@ExceptionHandler(MethodArgumentTypeMismatchException.class)
			public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
					MethodArgumentTypeMismatchException ex, WebRequest request) {
				log.error("MethodArgumentTypeMismatchException : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(),ex.getLocalizedMessage(),List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}

			@ResponseBody
			@ExceptionHandler(ConstraintViolationException.class)
			@ResponseStatus(HttpStatus.BAD_REQUEST)
			public ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException e) {
				log.error("ConstraintViolation : {}", e.getLocalizedMessage());
				List<String> errors = new ArrayList<>();
				for (ConstraintViolation<?> violation : e.getConstraintViolations())
				{
					errors.add(violation.getMessage());
				}
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), errors.get(0), List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}


			@ResponseBody
			@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
			@ResponseStatus(HttpStatus.BAD_REQUEST)
			public ResponseEntity<Object> onConstraintValidationExceptionHibernate(org.hibernate.exception.ConstraintViolationException ex) {
				log.error("ConstraintViolationException : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}


			@ExceptionHandler(NumberFormatException.class)
			public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex, WebRequest request) {
				log.error("NumberFormatException : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), List.of());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}


//			@ExceptionHandler(AuthenticationServiceException.class)
//			public ResponseEntity<Object> onAuthenticationException(AuthenticationServiceException e) {
//				log.error("AuthenticationServiceException : {}", e.getLocalizedMessage());
//				var response = Response.failedResponse(HttpStatus.UNAUTHORIZED.value(), ConstantMessages.TOKEN_EXPIRE);
//				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//			}


			@ExceptionHandler(JsonParseException.class)
			public final ResponseEntity<Object> handleJsonParseException(JsonParseException ex, WebRequest request) {
				log.error("JsonParseException : {}", ex.getLocalizedMessage());
				var error = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ConstantMessages.REQUEST_UNABLE_TO_PROCESS, ex.getLocalizedMessage());
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}



}





