package com.formacionbdi.microservicios.app.examenes.services;

import java.util.List;

import com.formacionbdi.microservicios.commnos.examenes.models.entity.Asignatura;
import com.formacionbdi.microservicios.commnos.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.comun.services.ComunContrato;

public interface ExamenService extends ComunContrato<Examen>{

	public List<Examen> findByNombre(String termino); // --> ExamenServiceImpl
	
	/*
	 * Va interactuar con varios repositorios al mismo tiempo, se le conoce como 
	 * DAO manager, podriamos tener varios atributos de diferentes DAO
	 */	
	public List<Asignatura> buscarTodasAsignatura();
	
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
	
}
