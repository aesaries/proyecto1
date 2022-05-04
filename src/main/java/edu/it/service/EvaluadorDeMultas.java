package edu.it.service;

import static com.bolivarsoft.sensorclima.TipoClima.LLUVIAS_MODERADAS;
import static com.bolivarsoft.sensorclima.TipoClima.LLUVIAS_TORRENCIALES;
import static com.bolivarsoft.sensorclima.TipoClima.NORMAL;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Auto;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Camion;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Moto;
import static com.bolivarsoft.sensorvelocidad.TipoVehiculo.Tractor;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import com.bolivarsoft.sensorvelocidad.TipoVehiculo;

import edu.it.annotations.Traceable;
import edu.it.entities.Ticket;
import edu.it.model.TipoClimaMasVehiculo;
import edu.it.repository.GrabadorDeTicket;

@Service
public class EvaluadorDeMultas {
	private Logger log = LoggerFactory.getLogger(getClass());

	private GrabadorDeTicket grabadorDeTicket;
	private HashMap<TipoClimaMasVehiculo, Integer> mapaLimites = new HashMap<>();

	public EvaluadorDeMultas(GrabadorDeTicket grabadorDeTicket) {
		this.grabadorDeTicket = grabadorDeTicket;
		
		mapaLimites.put(new TipoClimaMasVehiculo(NORMAL, Auto), 130);
		mapaLimites.put(new TipoClimaMasVehiculo(NORMAL, Camion), 90);
		mapaLimites.put(new TipoClimaMasVehiculo(NORMAL, Moto), 130);
		mapaLimites.put(new TipoClimaMasVehiculo(NORMAL, Tractor), 60);

		mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_MODERADAS, Auto), 110);
		mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_MODERADAS, Camion), 80);
		mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_MODERADAS, Moto), 110);
		mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_MODERADAS, Tractor), 60);

		mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_TORRENCIALES, Auto), 90);
        mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_TORRENCIALES, Camion), 70);
        mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_TORRENCIALES, Moto), 90);
        mapaLimites.put(new TipoClimaMasVehiculo(LLUVIAS_TORRENCIALES, Tractor), 60);
	}

	@Traceable
	public void evaluar(DatosVehiculo datosVehiculo, TipoClima tipoClima) {
		System.out.println("Evaluando...");
		System.out.println(datosVehiculo.patente);
		
		var key = new TipoClimaMasVehiculo(tipoClima, datosVehiculo.tipoVehiculo);
		var lim = mapaLimites.get(key);
		
		if (datosVehiculo.velocidadMedida > lim) {
			this.hacerTkt(datosVehiculo, tipoClima);
		}
		
		// this.segunClima(datosVehiculo, tipoClima);
		// Dejo para que quede la comparacion de ambos algoritmos
	}

	private void segunClima(DatosVehiculo datosVehiculo, TipoClima tipoClima) {
		switch (tipoClima.name()) {
			case "NORMAL":
				if ((datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Auto)
						|| datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Moto))
						&& datosVehiculo.velocidadMedida > 130) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Camion)
						&& datosVehiculo.velocidadMedida > 90) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Tractor)
						&& datosVehiculo.velocidadMedida > 60) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else {
					System.out.println("No corresponde multa");
				}
				break;
			case "LLUVIAS_MODERADAS":
				if ((datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Auto)
						|| datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Moto))
						&& datosVehiculo.velocidadMedida > 110) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Camion)
						&& datosVehiculo.velocidadMedida > 80) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Tractor)
						&& datosVehiculo.velocidadMedida > 60) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else {
					System.out.println("No corresponde multa");
				}
				break;
			case "LLUVIAS_TORRENCIALES":
				if ((datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Auto)
						|| datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Moto))
						&& datosVehiculo.velocidadMedida > 90) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Camion)
						&& datosVehiculo.velocidadMedida > 70) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else if (datosVehiculo.tipoVehiculo.equals(TipoVehiculo.Tractor)
						&& datosVehiculo.velocidadMedida > 60) {
					this.hacerTkt(datosVehiculo, tipoClima);
					System.out.println("Corresponde multa");

				} else {
					System.out.println("No corresponde multa");
				}
				break;
		}
	}

	// Lo pongo en un metodo
	private void hacerTkt(DatosVehiculo datosVehiculo, TipoClima tipoClima) {
		var tkt = new Ticket();
		tkt.id = UUID.randomUUID().toString();
		tkt.ts = System.currentTimeMillis() / 1000;
		tkt.patente = datosVehiculo.patente;
		tkt.tipoVehiculo = datosVehiculo.tipoVehiculo.toString();
		tkt.velocidadMedida = datosVehiculo.velocidadMedida;
		tkt.tipoClima = tipoClima.toString();
		tkt.limitePermitido = 90; // Obtenerlos o Hardcoded o algo externo

		/*
		 * 1) Lo primero es mapear todos los campos de datos vehiculo y de clima
		 * al ticket
		 * 2) Hacer una implementacion N de GrabadorDeTicket
		 * obviamente N implements GrabadorDeTicket
		 * 3) Hacer la logica para determinar si corresponde multa o no.
		 * (****) Fin de semana
		 */
		grabadorDeTicket.grabar(tkt);
	}
}