package com.formacionbdi.microservicios.app.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.examenes.models.repository.AsignaturaRepository;
import com.formacionbdi.microservicios.app.examenes.models.repository.ExamenRepository;
import com.formacionbdi.microservicios.commnos.examenes.models.entity.Asignatura;
import com.formacionbdi.microservicios.commnos.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.comun.services.ComunServiceImpl;

@Service
public class ExamenServiceImpl extends ComunServiceImpl<Examen, ExamenRepository> implements ExamenService {

	/*
	 * ERROR que se produce cuando no se inyecta el objeto recibido del controlador en el Service. 
	 *
	 * "trace": "java.lang.NullPointerException: 
	 * Cannot invoke \"com.formacionbdi.microservicios.app.examenes.models.repository.AsignaturaRepository.findAll()\" 
	 * because \"this.asignaturaRepository\" is null
	 */
	@Autowired
	private AsignaturaRepository asignaturaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String termino) {

		/*
		 * CR extends CrudRepository
		 * 
		 * @Autowired protected CR repositorioCrud;
		 * 
		 * @Query("select exm from Examen exm where exm.nombre like %?1%") public
		 * List<Examen> findByNombre(String termino);
		 */

		return repositorioCrud.findByNombre(termino);
	}


	
	/* 
	 * OPCION-1: Cambiar la interfaz «ExamenService» a un "Iterable<>", dado que "findAll()" retorna
	 * un Iterable y no habria la necesidad de convertlo con un CAST a "List<>"
	 *
	  @Override
	  @Transactional(readOnly=true) 
	  public Iterable<Asignatura> buscarTodasAsignatura(){
	  
	  		return asignaturaRepository.findAll();
	  }
	 */

	
	/*
	 * OPCION-2: Hacer un CAST a un "List<>", porque el "findAll()" retorna un
	 * Iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> buscarTodasAsignatura() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

}
