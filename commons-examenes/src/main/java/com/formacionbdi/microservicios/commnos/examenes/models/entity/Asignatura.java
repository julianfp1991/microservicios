package com.formacionbdi.microservicios.commnos.examenes.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="asignaturas")
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	

/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */
	// Atributo
	/*
	 * Cuando el atributo llama al hijo y viceversa se genera un proxy por debajo
	 * y este proxy se traducen en el JSON tambien maneja atributos los cuales pueden 
	 * generar conflictos, entonces se omiten los atributos:
	 * «handler» y «hibernateLazyInitializer»
	 */
	@JsonIgnoreProperties(value = {"hijos", "handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY) // muchas asignaturas "hijas" asociadas a un "padre"
	private Asignatura padre; // [Matematicas:]
	
	
/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */	
	/*
	 * cascade: Si se elimina un padre tambien se eliminan los hijos.
	 * mappedBy: relacion inversa
	 */
	@JsonIgnoreProperties(value = {"padre", "handler", "hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="padre", cascade = CascadeType.ALL) // una asignatura muchos hijos
	private List<Asignatura> hijos; // [Matematicas: {algebra, aritmetica, trigonometria}]


/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */
	
	public Asignatura() { // constructor

		this.hijos=new ArrayList<>();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */
	
	
	public Asignatura getPadre() {
		return padre;
	}

	public void setPadre(Asignatura padre) {
		this.padre = padre;
	}

	public List<Asignatura> getHijos() {
		return hijos;
	}

	public void setHijos(List<Asignatura> hijos) {
		this.hijos = hijos;
	}
	
	
	
	
}
