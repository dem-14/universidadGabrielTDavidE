/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.universidadGabrielTDavidE.rest;

import com.universidad.model.Profesor;
import com.universidad.repository.ProfesorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private final ProfesorRepository repository;

    public ProfesorController(ProfesorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Profesor> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Profesor buscar(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Profesor no encontrado"));
    }

    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return repository.save(profesor);
    }

    @PutMapping("/{id}")
    public Profesor actualizar(
            @PathVariable Long id,
            @RequestBody Profesor datos) {

        Profesor profesor = repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Profesor no encontrado"));

        profesor.setNombre(datos.getNombre());
        profesor.setApellidos(datos.getApellidos());
        profesor.setDireccion(datos.getDireccion());

        return repository.save(profesor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
