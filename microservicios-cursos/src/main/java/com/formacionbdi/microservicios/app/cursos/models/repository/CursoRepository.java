package com.formacionbdi.microservicios.app.cursos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.formacionbdi.microservicios.app.cursos.models.enity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	
	@Query("SELECT crs "
			+ "FROM Curso crs "
			+ "JOIN FETCH crs.cursoAlumno alm "
			+ "WHERE alm.alumnoId = ?1")
	public Curso findCursoByAlumnoIdRepository(Long id);
}
