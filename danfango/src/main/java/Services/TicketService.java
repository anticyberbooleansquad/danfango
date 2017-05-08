/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.TicketDAO;
import Model.Ticket;

@Service
public class TicketService {

	private TicketDAO ticketDAO;

	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

	
	@Transactional
	public void addTicket(Ticket u) {
		this.ticketDAO.addTicket(u);
	}

	
	@Transactional
	public void updateTicket(Ticket u) {
		this.ticketDAO.updateTicket(u);
	}

	
	@Transactional
	public List<Ticket> listTickets() {
		return this.ticketDAO.listTickets();
	}

	
	@Transactional
	public Ticket getTicketById(int id) {
		return this.ticketDAO.getTicketById(id);
	}
        
        @Transactional
	public Ticket getTicketByEmail(String email) {
		return this.ticketDAO.getTicketByEmail(email);
	}
	
	@Transactional
	public void removeTicket(int id) {
		this.ticketDAO.removeTicket(id);
	}
        	
}
