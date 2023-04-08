package com.formacionbdi.microservicios.app.respuestas.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends MongoRepository<Respuesta, Long> {

	@Query("{'alumnoId': ?0, "
			+ "'preguntaId': { $in: ?1} }") // Query de MongoDB
	public Iterable<Respuesta> findRespuestaByAlumnoByPreguntaIds(Long alumndoId, Iterable<Long> preguntaIds);
	
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

	@Query("SELECT r "
			+ "FROM Respuesta r "
			+ "JOIN FETCH r.pregunta p "
			+ "JOIN FETCH p.examen e "
			+ "WHERE r.alumnoId = ?1 AND e.id = ?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	
	/*
	 * Consulta para obtener los Ids de examanes respondidos por el alumno.
	 * 
	 * el fetch se utiliza cuando queremos retornar el objeto, con los demas objetos relacionados.
	 
	@Query("SELECT e.id "
			+ "FROM Respuesta r "
			+ "JOIN r.pregunta p "
			+ "JOIN p.examen e "
			+ "WHERE r.alumnoId = :alumnoId "
			+ "GROUP BY e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(@Param("alumnoId") Long alumnoId);*/
	
}
