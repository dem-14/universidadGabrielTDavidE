package com.example.universidadGabrielTDavidE.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "materia")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "creditos", nullable = false)
	private int creditoscantidad;

	@Column(name = "horas_clase", nullable = false)
	private int horascatedra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;

	// Constructores
	public Materia() {
	}

	public Materia(String nombre, int creditoscantidad, int horascatedra, Profesor profesor) {
		this.nombre = nombre;
		this.creditoscantidad = creditoscantidad;
		this.horascatedra = horascatedra;
		this.profesor = profesor;
		
	}

	// Getters y Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditoscantidad() {
		return creditoscantidad;
	}

	public void setCreditoscantidad(int creditoscantidad) {
		this.creditoscantidad = creditoscantidad;
	}

	public int getHorascatedra() {
		return horascatedra;
	}

	public void setHorascatedra(int horascatedra) {
		this.horascatedra = horascatedra;
	}

	
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
}
