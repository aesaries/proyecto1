package edu.it;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.bolivarsoft.sensorclima.SensorClima;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import com.bolivarsoft.sensorvelocidad.SensorVelocidad;

import edu.it.service.EvaluadorDeMultas;

public class InitProject implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SensorClima sensorClima;
	
	@Autowired 
	SensorVelocidad sensorVelocidad;
	
	@Autowired 
	EvaluadorDeMultas evaluadorDeMultas;
	
	@Bean
	public SensorClima initSensorClima() {
		return new SensorClima();
	}
	@Bean
	public SensorVelocidad sensorVelocidad() {
		return new SensorVelocidad();
	}
	
	public void run(String... args) throws Exception {
		System.out.println("Bienvenidos a Spring boot");
		
		logger.trace("Logger de trace");
		logger.debug("Logger de debug");
		logger.info("Logger de info");
		logger.warn("Logger de warning");
		logger.error("Logger de error");
		
		var clima = sensorClima.sensar();
		System.out.println(clima);
		System.out.println();
		
		for (;;) {
			DatosVehiculo datosVehiculo = sensorVelocidad.sensarVehiculo();
			System.out.println(datosVehiculo.patente);
			System.out.println(datosVehiculo.tipoVehiculo);
			System.out.println(datosVehiculo.velocidadMedida);
			System.out.println();
			evaluadorDeMultas.evaluar(datosVehiculo, clima);
		}
	}
}
