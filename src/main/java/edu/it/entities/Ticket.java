package edu.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
	@Id
	public String id;
	public Long ts;
	public String patente;
	@Column(name="tipo_de_vehiculo")
	public String tipoVehiculo;
	@Column(name="velocidad_medida")
	public Integer velocidadMedida;
	@Column(name="tipo_clima")
	public String tipoClima;
	@Column(name="limite_permitido")
	public Integer limitePermitido;
}
