public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
	
}

public interface AlumnoService{
	public Iterable<Alumno> buscarTodo();
}

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	private AlumnoRepository repository;
	
	public Iterable<Alumno> buscarTodo(){
		return repository
	}
}

@RestController
public class AlumnoController{
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok.body(service.buscarTodo());
	}
}


/*
Sí, inyectar la implementación AlumnoServiceImpl en lugar de la interfaz AlumnoService en la clase AlumnoController no es una buena práctica de programación, ya que se estaría acoplando la capa de presentación con la capa de lógica de negocio de forma innecesaria.

La inyección de dependencias se utiliza precisamente para evitar acoplamientos fuertes entre las diferentes capas de una aplicación, de manera que cada capa pueda cambiar sin afectar al resto. En este caso, la capa de presentación (controlador) debería depender solo de la interfaz AlumnoService, que es la capa de lógica de negocio, y no de su implementación AlumnoServiceImpl.

Además, la inyección de la implementación de AlumnoServiceImpl en AlumnoController haría que fuera más difícil realizar pruebas unitarias de la clase AlumnoController, ya que se estaría dependiendo de una implementación concreta en lugar de una abstracción.

Por lo tanto, lo correcto es inyectar la interfaz AlumnoService en la clase AlumnoController, tal como se muestra en el código original que compartiste.