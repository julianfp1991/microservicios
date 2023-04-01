package com.formacionbdi.microservicios.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/*
 * Comunicacion entre microservicios, en este caso Curso con Respuestas.
 * 
 * Para que pueda consumir el API Rest del Endpoint «obtenerExamenesIdsConRespuestasAlumno».
 * Pueden ser PathVariable y RequestBody.
 * 
 * Para obtener la listado de examenes respondido por el alumno y asi marcar
 * cada examen como respondido.
 * 
 * Feign es un cliente HTTP.
 */
@FeignClient(name="microservicio-respuestas") // Properties - microservicio Respuestas
public interface RespuestaFeignClient {

	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")	// Controlador de Respuesta
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId); // (1 Feign)
	
}
