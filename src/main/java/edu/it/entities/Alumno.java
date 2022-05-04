package edu.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="alumnos")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alumno {
    @Id
    public String id;
    public String nombre;
    public String apellido;
    public String calle;
    @Column(name="calle_numero")
    public String calleNumero;
    public String estado;
    public String pais;
}
