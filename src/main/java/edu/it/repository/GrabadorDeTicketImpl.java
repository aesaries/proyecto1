package edu.it.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.it.entities.Ticket;

@Repository
public class GrabadorDeTicketImpl implements GrabadorDeTicket {
	
	@Autowired
	TicketRepository ticketRepository;
	
	public void grabar(Ticket tkt) {
		System.out.println("Grabando ... ticket");
		System.out.println(tkt.toString());
		
		ticketRepository.save(tkt);
	}
}
