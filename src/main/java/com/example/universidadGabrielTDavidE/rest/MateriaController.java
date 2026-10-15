package com.example.universidadGabrielTDavidE.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.universidadGabrielTDavidE.dao.MateriaDAO;
import com.example.universidadGabrielTDavidE.dao.ProfesorDAO;
import com.example.universidadGabrielTDavidE.entity.Materia;
import com.example.universidadGabrielTDavidE.entity.Profesor;
import com.example.universidadGabrielTDavidE.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

	private MateriaDAO materiaDAO;
	private ProfesorDAO profesorDAO;

	@Autowired
	public MateriaController(MateriaDAO materiaDAO, ProfesorDAO profesorDAO) {
		this.materiaDAO = materiaDAO;
		this.profesorDAO = profesorDAO;
	}

	@GetMapping
	public List<Materia> getAll() {
		return materiaDAO.getAll();
	}

	@GetMapping("/{id}")
	public Materia getId(@PathVariable int id) {
		Materia materia = materiaDAO.getId(id);
		if (materia == null) {
			throw new ResourceNotFoundException("Materia con id no encontrada - " + id);
		}
		return materia;
	}

	@PostMapping
	@Transactional
	public Materia save(@RequestBody Materia materia) {
		if (materia == null) {
			throw new ResourceNotFoundException("No se puede eliminar. Materia no encontrada - Id: " + materia.getId());
		}
		// Verificación opcional por si envían un profesor en el cuerpo JSON
		verificarProfesorExistente(materia);

		return materiaDAO.save(materia);
	}

	@PutMapping
	@Transactional
	public Materia update(@RequestBody Materia materia) {
		// Validamos si la materia existe antes de intentar actualizar
		if (materiaDAO.getId(materia.getId()) == null) {
			throw new ResourceNotFoundException(
					"No se puede actualizar. Materia no encontrada - Id: " + materia.getId());
		}

		// Validamos que el profesor asociado exista en el sistema
		verificarProfesorExistente(materia);

		return materiaDAO.update(materia);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public String delete(@PathVariable int id) {
		Materia materia = materiaDAO.getId(id);
		if (materia == null) {
			throw new ResourceNotFoundException("No se puede eliminar. Materia no encontrada - Id: " + id);
		}
		materiaDAO.delete(id);
		return "Materia eliminada exitosamente - Id: " + id;
	}

	// Método privado de soporte para evitar código repetido en las validaciones de
	// llaves foráneas
	private void verificarProfesorExistente(Materia materia) {
		if (materia.getProfesor() != null && materia.getProfesor().getId() != 0) {
			long profId = materia.getProfesor().getId();
			// Buscamos el profesor y, si no existe, lanzamos la excepción inmediatamente
			Profesor profesor = profesorDAO.findById(profId).orElseThrow(() -> new ResourceNotFoundException(
					"Operación inválida. El profesor asignado con Id " + profId + " no existe."));

			// Si llegó aquí, el profesor existe y lo reasignamos completo
			materia.setProfesor(profesor);
		}
	}

}
