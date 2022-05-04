package edu.it.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import edu.it.entities.Ticket;

@Repository
public class Interceptor implements GrabadorDeTicket {

	@Autowired
	private GrabadorTicketJSON grabadorTicketJSON;
	
	@Autowired
	private GrabadorDeTicketImpl grabadorDeTicketImpl;
	
	@Override
	public void grabar(Ticket tkt) {
		try {
			grabadorTicketJSON.grabar(tkt);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			grabadorDeTicketImpl.grabar(tkt);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
