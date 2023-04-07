package com.formacionbdi.microservicios.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/* 
 * Para relacionar los cursos con los alumnos, hay que agregar
 * la clase Entity Alumnos mediante "@EntityScan", para luego hacer
 * la relacion en la bbdd (un curso a muchos alumnos)
 * 
 * Hay que tener en cuenta que este paquete curso tambien tiene su
 * propio Entity Curso, por ende, se debe agregar en la anotacion
 * "EntityScan" separado por coma
 */


// En EntityScan se tiene que agrega su propio Entity pues quedó sobreesctito con Commons Alumnos
//@EnableEurekaClient // opcional
@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.app.cursos.models.enity",
			 "com.formacionbdi.microservicios.commnos.examenes.models.entity"})
public class MicroserviciosCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursosApplication.class, args);
	}

}
