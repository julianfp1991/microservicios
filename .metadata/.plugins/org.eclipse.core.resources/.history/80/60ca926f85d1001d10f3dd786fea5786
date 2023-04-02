package com.formacionbdi.microservicios.app.cursos.services;


import org.springframework.web.bind.annotation.RequestParam;

import com.formacionbdi.microservicios.app.cursos.models.enity.Curso;
import com.formacionbdi.microservicios.commnos.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.comun.services.ComunContrato;

public interface CursoService extends ComunContrato<Curso> {
	
	/*
	 * public Iterable<Curso> buscarTodo();
	 * public Optional<Curso> findById(Long id); 
	 * public Curso guardar(Curso entity);
	 * public void deleteById(Long id);
	 */
	
	public Curso findCursoByAlumnoId(Long id);
	
	/*
	 * Cliente HTTP Feign para comunicacion con microservicio Curso.
	 * Listado de examenes respondidos.
	 */
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId); // (1 Feign)
	
	
	public Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids); // (2 Feign)
}
