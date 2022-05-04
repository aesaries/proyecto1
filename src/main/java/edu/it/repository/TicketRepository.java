package edu.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.it.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

}
