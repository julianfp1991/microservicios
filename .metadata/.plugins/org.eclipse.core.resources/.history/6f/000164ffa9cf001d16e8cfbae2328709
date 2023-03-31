package com.formacionbdi.microservicios.app.usuarios.services;


import java.util.List;

import com.formacionbdi.microservicios.commnos.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.comun.services.ComunContrato;

/*
import java.util.Optional;
import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
*/

// CONTRATO

/* Contratos: el término "contrato" se refiere a un conjunto de reglas 
 * y especificaciones que una interfaz debe seguir para que pueda ser 
 * implementada correctamente por un componente de Spring.
 * 
 * Por ejemplo, en Spring Data, las interfaces de repositorio definen un 
 * contrato para las operaciones CRUD (Create, Read, Update, Delete) que se 
 * pueden realizar en una entidad determinada. Para implementar un repositorio 
 * en Spring Data, se debe proporcionar una implementación que cumpla con el 
 * contrato definido por la interfaz de repositorio.
 */

public interface AlumnoServiceContrato extends ComunContrato<Alumno>{
	
	/*
	public Iterable<Alumno> buscarTodo();	
	public Optional<Alumno> findById(Long id); 
	public Alumno guardar(Alumno alumno);	
	public void deleteById(Long id);
	*/
	
	public List<Alumno> findByNombreOrApellido(String termino);
}
