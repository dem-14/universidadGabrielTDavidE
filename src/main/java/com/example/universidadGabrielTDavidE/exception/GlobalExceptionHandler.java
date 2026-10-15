package com.example.universidadGabrielTDavidE.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.NoResultException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Controla errores cuando getid, update o delete no encuentran el recurso
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exc) {
		return new ResponseEntity<>("Error: " + exc.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<String> handleNoResult(NoResultException ex) {
		return new ResponseEntity<>("Error: No se encontró el estudiante en la base de datos.", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		return new ResponseEntity<>("Error en los datos enviados: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("Error interno del servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}