package com.formacionbdi.microservicios.commnos.alumnos.models.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


/*
 * ANOTACIONES Spring boot
 * Una entidad (@Entity) o tabla (BD) en Java Persistence API (JPA) es una clase Java que 
 * representa un objeto persistente en una base de datos relacional. 
 * Las entidades se utilizan para mapear datos entre objetos Java y tablas de bases de datos, 
 * y se pueden manipular a través de la interfaz EntityManager de JPA.
 */
@Entity



/*
 * Se especifica el nombre de la tabla. Es opcional, si se omite el nombre de la
 * tabla sería el nombre de la clase "Alumno" pero en singular.
 */
@Table(name="alumnos") 
public class Alumno {
	
	
	/* 
	 * Se mapean los atributos (nombre, apellido y email) de la clase a campos/columnas en la tabla
	 * de la base de datos.
	 * 
	 * Para eso se usa @Id para indicar que este campo es una llave primaria en la BD.
	 * 
	 * GenerationType.IDENTITY: Este sirve para el proveedor de base de datos MySQL o PostgreSQL.
	 * 
	 * GenerationType.AUTO: Se adapata automaticamente al proveedor de base datos (SQL Server, Oracle).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // test cambiar a id_2 para prueba.
	
	
	/*
	 * @Column, especifica el nombre de campo/columna en la BD, sino se hace la
	 * anotación queda igual que los atributos; pero si quiere configurar algunos
	 * parametros como: cant caracteres, largo, size
	 * 
	 * @NotEmpty: valida de que el valor no venga vacio.
	 */
	@NotEmpty	// import jakarta.validation.constraints.NotEmpty;
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP) // Para indicar que la fecha sea completa (hora y fecha)
	private Date createAt;

	
	
	/*
	 * BLOB: Binary Large Objects
	 * 
	 * @Lob permite persistir un BLOB en este caso una foto. Y se va crear el campo «foto» en la bbdd.
	 * 
	 * El JSON en va volviendo mas extenso, por tanto, no es necesario mostrar este contenido en el JSON
	 * por tal motivo se usa "@JsonIgnore". Se mostrara la imagen en otro metodo controlador; aca solamente
	 * es para guardar la foto en la bbdd.
	 */
	@Lob
	@JsonIgnore
	private byte[] foto;
	
	
	
	/*	
	 * 	@PrePersist: es una anotación que se utiliza para marcar un método de una entidad 
	 * 	que se debe ejecutar antes de que se persista (guardar) en la base de datos. 
	 * 	Este método se utiliza comúnmente para inicializar algunos valores antes de guardar
	 *	en la bbdd en este caso la fecha actual.
	 */
	@PrePersist
	public void prePersis() {
		createAt = new Date();

	}
	
	
	/*   
	 * 			RETORNAR CODIGO DE LA FOTO
	 * 
	 * Este metodo es para que retorne un codigo/identificador de la foto.
	 * 
	 * Todo metodo GET de la clase Entity se va a serializar, se va a generar como
	 * un atributo en el JSON.
	 * 
	 * hashCode(): Es un metodo que genera un HASH un identificador unico y esta heredado
	 * de la clase Object. Todos los objetos en JAVA tienen un identificador unico; lo que permite
	 * comparar un objeto con otro, por ejemplo con el metodo «equals(Object obj)»
	 */
	public Integer getFotoHashCode() {
		
		/*
		 * si «foto» es distinto de null, entonces retornammos el HASH, de lo contrario
		 * no se retorna nada.
		 */
		return (this.foto != null) ? this.foto.hashCode() : null;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- */
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	/* ================================================================================ */	
	@Override
	public boolean equals(Object obj) {

		if(this == obj) { // los objetos son iguales
			return true;
		}
		
		// Si el objeto NO es una instancia de Alumno, entonces retornamos Falso.
		if(!(obj instanceof Alumno)) {
			return false;
		}
		
		// Si es una instancia se comparan los id's
		Alumno a = (Alumno) obj; 
		
		
		// que el ID sea disntinto de nulo y ya que es distinto de nulo se compara el id del objeto "a"
		return this.id != null && this.id.equals(a.getId());

	}

}
