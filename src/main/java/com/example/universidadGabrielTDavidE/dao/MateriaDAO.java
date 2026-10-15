package com.example.universidadGabrielTDavidE.dao;

import java.util.List;

import com.example.universidadGabrielTDavidE.entity.Materia;

public interface MateriaDAO {
    List<Materia> getAll();
    Materia getId(int id);
    Materia save(Materia materia);
    Materia update(Materia materia);
    void delete(int id);
}
