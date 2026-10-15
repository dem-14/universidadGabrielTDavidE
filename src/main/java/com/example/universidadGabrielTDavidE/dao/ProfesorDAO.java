/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.universidadGabrielTDavidE.dao;

import java.util.List;
import com.example.universidadGabrielTDavidE.entity.Profesor;
import java.util.Optional;

public interface ProfesorDAO {
    Profesor save(Profesor profesor);

    List<Profesor> findAll();

    Optional<Profesor> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
