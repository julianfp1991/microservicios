package com.formacionbdi.microservicios.comun.services;

/* 
 * Se convierte la interfaz "AlumnoServiceContrato" a un interfaz
 * generica "AlumnoServiceContrato"
 */


import java.util.Optional;
//import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/*
 * Este es el contrato, es decir los metodos que tiene que tener el Service
 * basicamente el CRUD.
 * 
 * Esto permite la manutencion y reutilizacion 
 * 
 * public interface AlumnoServiceContrato {
 * <>: API Generic de Java
 * ENT(InterfazAlumnoService): Entity
 */

public interface ComunContrato<ENT> {
	
	public Iterable<ENT> buscarTodo();
	
	public Page<ENT> buscarTodo(Pageable paginable);
	
	public Optional<ENT> buscarById(Long id); 
	
	public ENT guardar(ENT entity);
	
	public void deleteById(Long id);

}