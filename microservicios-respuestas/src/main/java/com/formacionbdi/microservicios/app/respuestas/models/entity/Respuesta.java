package com.formacionbdi.microservicios.app.respuestas.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formacionbdi.microservicios.commnos.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commnos.examenes.models.entity.Pregunta;

@Document(collection = "respuestas")
public class Respuesta {
	
	@Id
	private String id;
	
	private String texto;

	@Transient
	private Alumno alumno;

	private Long alumnoId;
	
	
	@Transient	// No esta mapeado a un atributo del documento
	private Pregunta pregunta;
	
	private Long preguntaId;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
/* ************ GETTERS Y SETTER DE LOS ATRIBUTOS Alumno ***************** */
	
	public Alumno getAlumno() {
		return alumno;
	}
	
	public void setAlumno(Alumno alumnoR) {
		this.alumno = alumnoR;
	}
		
	public Long getAlumnoId() {
		return alumnoId;
	}
	
	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}

	
/* ************ GETTERS Y SETTER DE LOS ATRIBUTOS Pregunta ***************** */
	
	public Pregunta getPregunta() {
		return pregunta;
	}
	
	public void setPregunta(Pregunta preguntaR) {
		this.pregunta = preguntaR;
	}

	public Long getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Long preguntaId) {
		this.preguntaId = preguntaId;
	}

}
