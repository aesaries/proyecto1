package edu.it.config;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import edu.it.entities.Alumno;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Utiles {
	private Faker fkr;
	
	public Alumno generarAlumnoRandom() {

		return Alumno.builder()
			.id(UUID.randomUUID().toString())
			.nombre(fkr.address().firstName())
			.apellido(fkr.address().lastName())
			.calle(fkr.address().streetName())
			.calleNumero(fkr.address().streetAddressNumber())
			.estado(fkr.address().state())
			.pais(fkr.address().country())
			.build();		 
	}
}
