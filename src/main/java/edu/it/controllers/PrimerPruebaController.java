package edu.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.it.config.Utiles;
import edu.it.entities.Alumno;
import edu.it.errores.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PrimerPruebaController {
	
	@Autowired
	private Utiles utiles;
	
	@GetMapping(path="/prueba")
	public Object esSoloUnaPrueba() {
		log.info("Entro en el metodo de esSoloUnaPrueba");
		return "Es solo una prueba";
	}
	
	
	
	@GetMapping(path="/inventar")
	public Alumno inventarAlumno() {
		return utiles.generarAlumnoRandom();
	}
	
	@PostMapping(path="dameerror")
	public void generarUnErrorEnPost() {
		throw new RuntimeException("Se produjo un error");
	}
	
	@PutMapping(path="noseencuentra")
	public void generarError404() {
		throw new NotFoundException();
	}
}
