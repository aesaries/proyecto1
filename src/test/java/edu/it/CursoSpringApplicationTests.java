package edu.it;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolivarsoft.sensorclima.TipoClima;
import com.bolivarsoft.sensorvelocidad.DatosVehiculo;
import com.bolivarsoft.sensorvelocidad.TipoVehiculo;

import edu.it.entities.Ticket;
import edu.it.repository.GrabadorDeTicket;
import edu.it.service.EvaluadorDeMultas;
import io.jsonwebtoken.lang.Assert;

class GrabadorDeTicketStub implements GrabadorDeTicket {
	private Boolean llamoGrabar = false;
	
	public void grabar(Ticket tkt) {
		llamoGrabar = true;
	}
	public Boolean llamoGrabar() { 
		return this.llamoGrabar; 
	}
}

@SpringBootTest(classes = { java.util.Date.class, GrabadorDeTicket.class })
class CursoSpringApplicationTests {

	/*
	 * AAA
	 * Arrange
	 * Act
	 * Assert
	 */
	
	@Test
	void autoCondicionesLluviaTorrencialExcesoVelocidad() {
		var grabadorStub = new GrabadorDeTicketStub();
		var evaluador = new EvaluadorDeMultas(grabadorStub);
	
		DatosVehiculo dv = new DatosVehiculo();
		dv.patente = "N/A";
		dv.velocidadMedida = 120;
		dv.tipoVehiculo = TipoVehiculo.Auto;
	
		evaluador.evaluar(dv, TipoClima.LLUVIAS_MODERADAS);
		
		Assert.isTrue(grabadorStub.llamoGrabar(), "Atencion que debio ser multa y no lo multe");
	}
	@Test
	void camionConExcesoVelocidad() {
		var mockGrabador = Mockito.mock(GrabadorDeTicket.class);
		var evaluador = new EvaluadorDeMultas(mockGrabador);
		
		DatosVehiculo dv = new DatosVehiculo();
		dv.patente = "N/A";
		dv.velocidadMedida = 90;
		dv.tipoVehiculo = TipoVehiculo.Camion;
		
		evaluador.evaluar(dv, TipoClima.LLUVIAS_TORRENCIALES);
		
		Mockito.verify(mockGrabador, Mockito.times(1)).grabar(Mockito.any());
	}
}
