package com.formacionbdi.microservicios.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private RespuestaRepository repositorio;
	
	
	@Override
	@Transactional	// org.springframework.transaction.annotation.Transactional;
	public Iterable<Respuesta> guardarTodo(Iterable<Respuesta> respuestas) {

		return repositorio.saveAll(respuestas);
	}


	@Override
	@Transactional(readOnly= true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {

		return repositorio.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}


	@Override
	@Transactional(readOnly= true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {

		return repositorio.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
