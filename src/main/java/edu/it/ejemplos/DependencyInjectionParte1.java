package edu.it.ejemplos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

class Factory {
	public static Pago crearObjetos() {
		return new Pago(new PersistePagosImpl2());
	}
}

@Component
class Pago {
	private PersistePagos persistePagos;

	public Pago(PersistePagos persistePagos) {
		this.persistePagos = persistePagos;
	}

	public void pagar() {
		System.out.println("Antes de persistir el pago");
		persistePagos.persistir(10000);
		System.out.println("Despues de persistir el pago");
	}
}

interface PersistePagos {
	public void persistir(Integer i); 
}

@Primary
@Component
class PersistePagosImpl implements PersistePagos {
	public void persistir(Integer i) {
		System.out.println("Voy a persistir el pago");
	}
}

@Component
class PersistePagosImpl2 implements PersistePagos {
	public void persistir(Integer i) {
		System.out.println("Voy a persistir el pago - Version 2");
	}
}

@Component
public class DependencyInjectionParte1 {
	@Autowired
	Pago pago;
	
	public void run() {
		pago.pagar();
	}
}
