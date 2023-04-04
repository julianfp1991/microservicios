package com.formacionbdi.microservicios.comun.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.microservicios.comun.services.ComunContrato;

import jakarta.validation.Valid;


public class CommonController <ENT, SRV extends ComunContrato<ENT>> {
	
	
	@Autowired
	protected SRV servicio;
		//CursoService servicio
	
	
	/*
	 * Se inician las peticiones de tipo Get/Obtener "Mapping", permite
	 * mapear(relacionar), una URL/HTTP al método
	 */
	@GetMapping("/") // Se lista todo
	public ResponseEntity<?> listar(){
		
		return ResponseEntity.ok().body(servicio.buscarTodo());
	}
	
	
	@GetMapping("/pagina") // Aca se lista con paginacion
	public ResponseEntity<?> listar(Pageable paginable){
		
		return ResponseEntity.ok().body(servicio.buscarTodo(paginable));
	}
	
	
	@GetMapping("/{id}") // http://miaplicacion.com/mi-recurso/123
	public ResponseEntity<?> ver(@PathVariable(name = "id") Long idEntity){ // debe recibir parametro; un id para buscar en la BD.

//	public ResponseEntity<?> ver(@PathVariable Long id){ // OPCIÓN 1 - cuando el id es igual al del argumento.

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Optional<ENT> idEncontrado = servicio.buscarById(idEntity); // OPCIÓN 2
//		Optional<Alumno> siHayDatos = servicio.findById(id); // OPCIÓN 1 - cuando el id es igual al del argumento.
				
		if(idEncontrado.isEmpty()) {
			
			/* 
			 * "build", construye la respuesta con un body vacío. 
			 * 	HTTP 404 Not Found
			 */
			return ResponseEntity.notFound().build();
		}
		else {
			
			return ResponseEntity.ok(idEncontrado); // 200 OK.
		}
		
	}
	
	
/* ============= Se inician las peticiones de tipo Post/Crear ================================ */
	
	/*
	 * public ResponseEntity <?> crear(@RequestBody ENT entity){
	 * 
	 * Se modifica el controlador generico, heredan los demas controladores, que implementan
	 * el CRUD, para validar y obtener los mensaje de error y retorne un respuesta JSON.
	 * 
	 * Se adiciona la anotacion "@Valid", que esta en la dependencia:
	 * <artifactId>spring-boot-starter-validation</artifactId>
	 * 
	 * BindingResult resultado: Debe ir siempre despues de la Entidad, para obtener
	 * los mensajes de error.
	 */
	@PostMapping("/")
	public ResponseEntity<?> crear(@Valid @RequestBody ENT entity, BindingResult resultado){
		
		// Ahora se valida antes de guardar usando el metodo "validar"
		if(resultado.hasErrors()) {
			return this.validar(resultado);
		}
		
		ENT entityDb = servicio.guardar(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb); // 201
	}
	
	
	
/* =============== Se inician las peticiones de tipo Put/Editar ================================ */
	
	@DeleteMapping
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		
		servicio.deleteById(id); // void
		return ResponseEntity.noContent().build(); // 204 No Content.

	}
	
	
/* =============== Validar los errores ================================ */	
	
	/*
	 * Va retornar los mensajes con errores, por ejemplo:
	 * el campo no debe estar vacio; el correo tiene un formato incorrecto.
	 */
	protected ResponseEntity<?> validar(BindingResult resultado){
		
		/*
		 * Map<String, Integer> miMapa = new HashMap<>();
		 * 
		 * 	Agregamos elementos al mapa
		 *
		 * 	miMapa.put("Juan", 25);
		 * 	miMapa.put("Ana", 30);
		 * 	miMapa.put("Pedro", 42);
		 * 
		 * 	Accedemos al valor de un elemento a través de su clave
		 *
		 * 	int edadAna = miMapa.get("Ana");
		 * 		System.out.println("La edad de Ana es " + edadAna);
		 */
		Map<String, Object> errores = new HashMap<>();
		
		/*
		 *  List<FieldError> getFieldErrors();
		 *  
		 *  usando el "resultado" obtenemos las lista de errores y se recorre con el forEach
		 *  y lo guardamos en el Mapa con el put con el nombre del campo y el mensaje de error.
		 *  
		 *  y por ultimo se pasa a la respuesta en el ResponseEntity
		 */
		resultado.getFieldErrors()
			.forEach(err -> 
				{
					errores.put(err.getField(), " El campo "+err.getField()+" "+err.getDefaultMessage());
				}
			);
		
			return ResponseEntity
					.badRequest()
					.body(errores);
	}
}
