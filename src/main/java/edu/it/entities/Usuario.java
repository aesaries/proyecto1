package edu.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Usuario {
	@Id
	public String id;
	@Column(name="nombre_usuario")
	public String nombre;
	public String password;
	public String salt;
	public String role;
}
