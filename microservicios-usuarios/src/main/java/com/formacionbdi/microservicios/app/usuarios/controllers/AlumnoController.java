package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionbdi.microservicios.app.usuarios.services.AlumnoServiceContrato;
import com.formacionbdi.microservicios.commnos.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.comun.controllers.CommonController;

import jakarta.validation.Valid;


/* Esta anotación marca el controlador del tipo REST 
 * 
 * */
@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoServiceContrato>  {
	
	
	/*
	 * @Autowired private AlumnoServiceContrato servicio; // Tipo generico de
	 * interfaz.
	 *	
	 *
	 * Se inician las peticiones de tip o Get/Obtener
	 * 
	 * 
	 * "Mapping", permite mapear(relacionar) una URL/HTTP al método
	 * 
	 * En el siguiente "@GetMapping", no se coloca ruta, por tanto, se iría a la
	 * raíz
	 * 
	 * @GetMapping("/")
	 * public ResponseEntity<?> listar(){ // No recibe parametro
	 * porque lista todo desde BD.
	 * 
	 * return ResponseEntity.ok().body(servicio.buscarTodo( )); }
	 */
	
	
	/*
	 * @GetMapping("/{id}") // http://miaplicacion.com/mi-recurso/123 public
	 * ResponseEntity<?> ver(@PathVariable(name = "id") Long idAlumno){ // debe
	 * recibir parametro; un id para buscar en la BD.
	 * 
	 * // public ResponseEntity<?> ver(@PathVariable Long id){ // OPCIÓN 1
	 * 
	 * Optional<Alumno> idEncontrado = servicio.findById(idAlumno); // OPCIÓN 2
	 * 
	 * // Optional<Alumno> siHayDatos = servicio.findById(id); // OPCIÓN 1
	 * 
	 * 
	 * if(idEncontrado.isEmpty()) {
	 * 
	 * return ResponseEntity.notFound().build(); "build", construye la respuesta con
	 * un body vacío. HTTP 404 Not Found
	 * 
	 * 
	 * } else {
	 * 
	 * return ResponseEntity.ok(idEncontrado); // 200 OK. }
	 * 
	 * }
	 */
	
	
	/*
	 * Se inician las peticiones de tipo Post/Crear
	 * 
	 * @PostMapping("/") public ResponseEntity <?> crear(@RequestBody Alumno
	 * alumno){
	 * 
	 * Alumno alumnoDb = servicio.guardar(alumno);
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb); // 201 }
	 */
	
	
	 /* 
	 * Se inician las peticiones de tipo Put/Editar
	 *
	 * La anotación "@PathVariable" se utiliza para indicar que el valor del 
	 * parámetro id debe leerse de la ruta de la URL.
	 */
	@PutMapping("/{id}")
	public ResponseEntity <?> editar(@Valid @RequestBody Alumno alumno, BindingResult resultado, @PathVariable Long id){
		
		// Ahora se valida antes de editar usando el metodo "validar"
		if(resultado.hasErrors()) {
			return this.validar(resultado);
		}
		
		Optional<Alumno> siHayDatos = servicio.buscarById(id);
		
			if(siHayDatos.isEmpty()) {
			
				return ResponseEntity.notFound().build();
			}
			else {
				
				Alumno alumnoDb = siHayDatos.get(); // Optional.get()
				
				alumnoDb.setNombre(alumno.getNombre());
				alumnoDb.setApellido(alumno.getApellido());
				alumnoDb.setEmail(alumno.getEmail());
				
				//return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
				return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar(alumnoDb));
			}
	}
	
	
	/*
	 * Se inician las peticiones de tipo Put/Editar
	 * 
	 * @DeleteMapping public ResponseEntity <?> eliminar(@PathVariable Long id){
	 * 
	 * servicio.deleteById(id); // void return ResponseEntity.noContent().build();
	 * // 204 No Content.
	 * 
	 * }
	 */
	

/* =========== ENDPOINT - Para buscar por nombre o apellido ======================= */
	
	
	// String termino es el nombre del argumento
	@GetMapping("/filtrar/{expresion}")
	public ResponseEntity<?> filtrar(@PathVariable(name= "expresion") String termino2){
		return ResponseEntity.ok(servicio.findByNombreOrApellido(termino2));
	}



/* =========== ENDPOINT - Para crear el usuario con foto ======================= */	
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult resultado, 
			@RequestParam MultipartFile archivo) throws IOException {
		
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		
		/*
		 * Con «super.crear» estamos heredando de la super clase «Crear», le pasamos el Alumno y el resultado (validacion).
		 * 
		 * En este metodo unicamente se recibe la foto; todo lo demas de crear se pasa al otro metodo padre Crear.
		 */
		return super.crear(alumno, resultado);
	}
	

	
/* =========== ENDPOINT - Para editar el usuario con foto ======================= */		

	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity <?> editar(@Valid Alumno alumno, BindingResult resultado, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException{
		
		
		// Ahora se valida antes de editar usando el metodo "validar"
		if(resultado.hasErrors()) {
			return this.validar(resultado);
		}
		
		Optional<Alumno> siHayDatos = servicio.buscarById(id);
		
			if(siHayDatos.isEmpty()) {
			
				return ResponseEntity.notFound().build();
			}
			else {
				
				Alumno alumnoDb = siHayDatos.get(); // Optional.get()
				
				alumnoDb.setNombre(alumno.getNombre());
				alumnoDb.setApellido(alumno.getApellido());
				alumnoDb.setEmail(alumno.getEmail());
				
				if(!archivo.isEmpty()) {
					alumnoDb.setFoto(archivo.getBytes());
				}
				
				return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar(alumnoDb));
			}
	}
	
	
/* ================= CONTROLADOR PARA VER LA FOTO DEL ALUMNO ======================================= */
	
	@GetMapping("/cargas-fotos/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		
		Optional<Alumno> hayDatos = servicio.buscarById(id);
		
		/*
		 * El «hayDatos» trae el valor encontrado en la base de datos
		 * 
		 * Con el condicional If, se valida ID no existe o si esta el ID del alumno, que este no tenga foto
		 * para retornar que "NOT FOUND"
		 */
		if(hayDatos.isEmpty() || hayDatos.get().getFoto() == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(hayDatos.get().getFoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
			
	}
	
	
	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
		
		return ResponseEntity.ok(servicio.buscarTodoPorId(ids));
		// return ResponseEntity.ok().body(servicio.buscarTodoPorId(ids));
	}
	
}