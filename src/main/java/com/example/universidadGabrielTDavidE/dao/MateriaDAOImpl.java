package com.example.universidadGabrielTDavidE.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.universidadGabrielTDavidE.entity.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MateriaDAOImpl implements MateriaDAO {

    private final EntityManager entityManager;

    @Autowired
    public MateriaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Materia> getAll() {
        // Ejecuta un query JPQL para traer todas las materias de la base de datos
        TypedQuery<Materia> query = entityManager.createQuery("FROM Materia", Materia.class);
        return query.getResultList();
    }

    @Override
    public Materia getId(long id) {
        return entityManager.find(Materia.class, id);
    }

    @Override
    public Materia save(Materia materia) {
        entityManager.persist(materia);
        return materia;
    }

    @Override
    public Materia update(Materia materia) {
        return entityManager.merge(materia);
    }

    @Override
    public void delete(long id) {
        Materia materia = entityManager.find(Materia.class, id);
        if (materia != null) {
            entityManager.remove(materia);
        }
    }
}
