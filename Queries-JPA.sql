/* Consulta @Query - JPA */
SELECT crs
FROM Curso crs
JOIN FETCH crs.alumnos alm
WHERE alm.id = ?1

/* CONSULTA DIRECTA EN LA BD - NAVICAT */
SELECT
	cursos.*, 
	alumnos.*
FROM
	cursos
	INNER JOIN
	cursos_alumnos
	ON 
		cursos.id = cursos_alumnos.curso_id
	INNER JOIN
	alumnos
	ON 
		cursos_alumnos.alumnos_id = alumnos.id
WHERE
	cursos_alumnos.alumnos_id = 3




/* ===== public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId); ========== */

/* Consulta @Query - JPA */
SELECT r
FROM Respuesta r
JOIN FETCH r.alumno a
JOIN FETCH r.pregunta p
JOIN FETCH p.examen e
WHERE a.id = :alumnoId AND e.id = :examenId


/* Consulta nativa en MariaDB por consola */
SELECT 
	r1_0.id,
	a1_0.id,
	a1_0.apellido,
	a1_0.create_at,
	a1_0.email,
	a1_0.foto,
	a1_0.nombre,
	p1_0.id,
	e1_0.id,
	e1_0.create_at,
	e1_0.asignatura_id,
	e1_0.nombre,
	p1_0.texto,
	r1_0.texto 
FROM
	respuestas r1_0 
JOIN 
	alumnos a1_0
	ON a1_0.id = r1_0.alumno_id
JOIN preguntas p1_0
	ON p1_0.id = r1_0.pregunta_id
JOIN examenes e1_0 
	ON e1_0.id = p1_0.examen_id 
WHERE 
	r1_0.alumno_id = ? AND p1_0.examen_id = ?


/* CONSULTA DIRECTA EN LA BD - NAVICAT */
SELECT
	respuestas.*, 
	preguntas.*, 
	examenes.*, 
	alumnos.*
FROM
	respuestas
	INNER JOIN
	preguntas
	ON 
		respuestas.pregunta_id = preguntas.id
	INNER JOIN
	examenes
	ON 
		preguntas.examen_id = examenes.id
	INNER JOIN
	alumnos
	ON 
		respuestas.alumno_id = alumnos.id
WHERE
	respuestas.alumno_id = 2 AND
	examenes.id = 1




/* ===== public Iterable<Long> findExamenesIdsConRespuestasByAlumno(@Param("alumnoId") Long alumnoId); ========== */

/* Consulta de JPA */
SELECT e.id
FROM Respuesta r
JOIN r.alumno a
JOIN p.examen e
WHERE a.id = :alumnoId
GROUP BY e.id


/* Consulta nativa de MariaDB por consola */
SELECT
	p1_0.examen_id 
FROM
	respuestas r1_0 
JOIN 
	alumnos a1_0 
	ON a1_0.id = r1_0.alumno_id 
JOIN 
	preguntas p1_0 
	ON p1_0.id = r1_0.pregunta_id 
JOIN 
	examenes e1_0 
	ON e1_0.id = p1_0.examen_id 
WHERE 
	r1_0.alumno_id = ? 
GROUP BY 
	p1_0.examen_id
	
	
/* Consulta directa en base de datos con NAVICAT */
SELECT
	preguntas.examen_id
FROM
	respuestas
	INNER JOIN
	alumnos
	ON 
		respuestas.alumno_id = alumnos.id
	INNER JOIN
	preguntas
	ON 
		respuestas.pregunta_id = preguntas.id
	INNER JOIN
	examenes
	ON 
		preguntas.examen_id = examenes.id
WHERE
	respuestas.alumno_id = 2
GROUP BY
	preguntas.examen_id