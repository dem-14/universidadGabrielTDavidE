/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.universidadGabrielTDavidE.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.universidadGabrielTDavidE.entity.Materia;
import com.example.universidadGabrielTDavidE.entity.Profesor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 *
 * @author gbrlt
 */
@Repository
@Transactional
public class ProfesorDAOImpl implements ProfesorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Profesor save(Profesor profesor) {
		if (profesor.getId() == null) {
			entityManager.persist(profesor);
			return profesor;
		}

		return entityManager.merge(profesor);
	}

	@Override
	@Transactional(Transactional.TxType.SUPPORTS)
	public List<Profesor> findAll() {
		return entityManager.createQuery("SELECT p FROM Profesor p ORDER BY p.id", Profesor.class).getResultList();
	}

	@Override
	@Transactional(Transactional.TxType.SUPPORTS)
	public Optional<Profesor> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Profesor.class, id));
	}

	@Override
	@Transactional(Transactional.TxType.SUPPORTS)
	public boolean existsById(Long id) {
		return entityManager.find(Profesor.class, id) != null;
	}

	@Override
	public void deleteById(Long id) {
		Profesor profesor = entityManager.find(Profesor.class, id);

		if (profesor != null) {
			entityManager.remove(profesor);
		}
	}

	@Override
	@Transactional
	// @Transactional(readOnly = true) // Optimiza la consulta al ser solo de
	// lectura
	public List<Materia> findMateriasByProfesorId(Long profesorId) {
		// Forzamos el JOIN entre Materia y Profesor basándonos en la relación declarada
		String jpql = "SELECT m FROM Materia m JOIN m.profesor p WHERE p.id = :profesorId";
		TypedQuery<Materia> query = entityManager.createQuery(jpql, Materia.class);
		query.setParameter("profesorId", profesorId);
		return query.getResultList();
	}
}
