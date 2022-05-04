package edu.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.it.entities.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

}
