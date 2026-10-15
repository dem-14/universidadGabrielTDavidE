/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.universidadGabrielTDavidE.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.universidadGabrielTDavidE.dao.ProfesorDAO;
import com.example.universidadGabrielTDavidE.entity.Materia;
import com.example.universidadGabrielTDavidE.entity.Profesor;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorRestController {
	private final ProfesorDAO profesorDAO;

	public ProfesorRestController(ProfesorDAO profesorDAO) {
		this.profesorDAO = profesorDAO;
	}

	// Listar todos los profesores
	@GetMapping
	public List<Profesor> listar() {
		return profesorDAO.findAll();
	}

	// Buscar un profesor por su ID
	@GetMapping("/{id}")
	public Profesor buscar(@PathVariable Long id) {
		return profesorDAO.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));
	}

	// Registrar un profesor
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Profesor crear(@RequestBody Profesor profesor) {

		validarProfesor(profesor);

		profesor.setNombre(profesor.getNombre().trim());
		profesor.setApellidos(profesor.getApellidos().trim());

		return profesorDAO.save(profesor);
	}

	// Actualizar un profesor
	@PutMapping("/{id}")
	public Profesor actualizar(@PathVariable Long id, @RequestBody Profesor datos) {

		validarProfesor(datos);

		Profesor profesor = profesorDAO.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));

		profesor.setNombre(datos.getNombre().trim());
		profesor.setApellidos(datos.getApellidos().trim());
		profesor.setDireccion(datos.getDireccion());

		return profesorDAO.save(profesor);
	}

	// Eliminar un profesor
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {

		if (!profesorDAO.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
		}

		profesorDAO.deleteById(id);
	}

	// Validar los datos obligatorios
	private void validarProfesor(Profesor profesor) {

		if (profesor.getNombre() == null || profesor.getNombre().isBlank() || profesor.getApellidos() == null
				|| profesor.getApellidos().isBlank()) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre y los apellidos son obligatorios");
		}
	}

	@GetMapping("/{id}/materias")
	public List<Materia> getMateriasByProfesorId(@PathVariable("id") Long id) {
		// 1. Validamos primero si el profesor realmente existe en la BD
		if (!profesorDAO.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
		}

		// 2. Retornamos la lista de materias de manera directa
		return profesorDAO.findMateriasByProfesorId(id);
	}
}
