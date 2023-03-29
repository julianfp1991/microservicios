package com.formacionbdi.microservicios.comun.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.stereotype.Service;
//import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
//import com.formacionbdi.microservicios.app.usuarios.models.repository.AlumnoRepository;


/* Esto se usa para reutilizar código mediante herencia o subclases
 * 
 
//@Service
public class ComunServiceImpl<ENT, CR extends CrudRepository<ENT, Long>> implements ComunContrato<ENT> {

 * "CR" es una subclase de "CrudRepository" que se puede usar 
 * para acceder a los datos de la entidad "ENT", por ejemplo
 * 
 * Curso extends CR (CrudRepository) luego creara objeto repositorioCrud
 * Alumno extends CR (CrudRepository) luego creara objeto repositorioCrud
 */


// PagingAndSortingRepository --> ListPagingAndSortingRepository --> JpaRepository
public class ComunServiceImpl<ENT, CR extends JpaRepository<ENT, Long>> implements ComunContrato<ENT> {

	
/* Ahora se procede a inyectar el repositorio con "@Autowired"
 * creación del constructor que inyecta la dependencia
*/
	@Autowired
	protected CR repositorioCrud;
	
	
	
/* Los metodos de consulta se anotan con "@Transactional" de lectura,
 * por ejemplo para Select, con "(readOnly = true)".
 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<ENT> buscarTodo() { // esto es de ComunContrato

		return repositorioCrud.findAll(); // esto es propio del CRUD
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<ENT> buscarById(Long id) {

		return repositorioCrud.findById(id);
	}

	
	@Override
	@Transactional
	public ENT guardar(ENT entity) {

		return repositorioCrud.save(entity);
	}

	
	@Override
	@Transactional
	public void deleteById(Long id) {
		repositorioCrud.deleteById(id);

	}

	// PagingAndSortingRepository --> ListPagingAndSortingRepository --> JpaRepository
	@Override
	@Transactional(readOnly = true)
	public Page<ENT> buscarTodo(Pageable paginable) {
		return repositorioCrud.findAll(paginable);
	}

}
