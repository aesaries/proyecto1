package edu.it.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import edu.it.entities.Ticket;

@Repository
@Primary
public class InterceptorAlfa implements GrabadorDeTicket {
	@Value("${ozark.permiteGrabarSQL}")
	private Boolean permiteGrabarSQL;
	
	@Autowired // este es el camino normal
	private Interceptor Interceptor;
	
	@Autowired // Camino eceptional
	private GrabadorTicketJSON grabadorTicketJSON;

	@Override
	public void grabar(Ticket tkt) {
		
		if (permiteGrabarSQL) {
			Interceptor.grabar(tkt);
		}
		else {
			grabadorTicketJSON.grabar(tkt);
		}
	}
}
