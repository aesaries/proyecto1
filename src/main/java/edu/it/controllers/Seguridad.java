package edu.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.it.entities.Usuario;
import edu.it.model.UsuPass;
import edu.it.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class Seguridad {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(path = "/crearUsuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void postSobreAlumno(@RequestBody Usuario usuario) {
        log.info(usuario.toString());
        usuarioRepository.save(usuario);
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody UsuPass usuPass) {
        log.info(usuPass.usuario);
        log.info(usuPass.password);
        log.info("Probando login");
    }
}
