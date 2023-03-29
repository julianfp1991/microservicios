package com.formacionbdi.microservicios.app.respuestas.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

	/*
	 * «?1» --> alumnoId
	 * «?2» --> examenId
	 * 
	 * se utiliza para buscar todas las respuestas de un alumno especifico para un examen especifico.
	 * 
	 * «join fetch r.alumno a»: Aqui se esta haciendo un JOIN con la relacion "alumno" de la clase "Respuesta". 
	 * La clausula fetch se utiliza para recuperar los datos de alumno en la misma consulta, 
	 * lo que puede ayudar a mejorar el rendimiento.
	 * 
	 * Aca se retorna el r (respuesta) con el Fetch Alumno y con Fetch Pregunta y la pregunta con su Fetch Examen.
	 */
	@Query("SELECT r "
			+ "FROM Respuesta r "
			+ "JOIN FETCH r.alumno a "
			+ "JOIN FETCH r.pregunta p "
			+ "JOIN FETCH p.examen e "
			+ "WHERE a.id = ?1 AND e.id = ?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	
	/*
	 * Consulta para obtener los Ids de examanes respondidos por el alumno.
	 * 
	 * el fetch se utiliza cuando queremos retornar el objeto, con los demas objetos relacionados.
	 */
	@Query("SELECT e.id "
			+ "FROM Respuesta r "
			+ "JOIN r.alumno a "
			+ "JOIN r.pregunta p "
			+ "JOIN p.examen e "
			+ "WHERE a.id = :alumnoId "
			+ "GROUP BY e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(@Param("alumnoId") Long alumnoId);
	
}
