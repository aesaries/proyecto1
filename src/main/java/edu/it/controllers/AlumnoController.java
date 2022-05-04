package edu.it.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.it.entities.Alumno;
import edu.it.errores.NotFoundException;
import edu.it.repository.AlumnoRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/alumno")
@Slf4j
public class AlumnoController {
	@Autowired
	AlumnoRepository alumnoRepository; 
	
	@GetMapping
	public List<Alumno> getTodos() {
		return alumnoRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public Alumno getUnAlumno(@PathVariable("id") String id) {
		Optional<Alumno> optAlu = alumnoRepository.findById(id);
		
		if (optAlu.isPresent()) {
			return optAlu.get();
		}
		
		throw new NotFoundException();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postSobreAlumno(@RequestBody Alumno alumno) {
		log.info(alumno.toString());
		alumnoRepository.save(alumno);
	}
	
	@PutMapping(path="/{id}")
	public void putSobreAlumno(@RequestBody Alumno alumno, 
								@PathVariable("id") String id) {
		
		try {
			alumnoRepository.getById(id);
			alumnoRepository.save(alumno);
		}
		catch (EntityNotFoundException ex) {
			throw new NotFoundException();
		}
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteSobreAlumno(@PathVariable("id") String id) {
		try {
			var alu = alumnoRepository.getById(id);
			alumnoRepository.delete(alu);
		}
		catch (EntityNotFoundException ex) {
			throw new NotFoundException();
		}
	}
}
