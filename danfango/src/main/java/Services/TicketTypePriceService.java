/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.TicketTypePriceDAO;
import Model.Theatre;
import Model.TicketTypePrice;

@Service
public class TicketTypePriceService {

	private TicketTypePriceDAO ticketTypePriceDAO;

	public void setTicketTypePriceDAO(TicketTypePriceDAO ticketTypePriceDAO) {
		this.ticketTypePriceDAO = ticketTypePriceDAO;
	}

	
	@Transactional
	public void addTicketTypePrice(TicketTypePrice u) {
		this.ticketTypePriceDAO.addTicketTypePrice(u);
	}

	
	@Transactional
	public void updateTicketTypePrice(TicketTypePrice u) {
		this.ticketTypePriceDAO.updateTicketTypePrice(u);
	}

	
	@Transactional
	public List<TicketTypePrice> listTicketTypePrices() {
		return this.ticketTypePriceDAO.listTicketTypePrices();
	}

	
	@Transactional
	public TicketTypePrice getTicketTypePriceById(int id) {
		return this.ticketTypePriceDAO.getTicketTypePriceById(id);
	}
        
        @Transactional
	public List<TicketTypePrice> getTicketTypePriceByTheatre(Theatre theatre) {
		return this.ticketTypePriceDAO.getTicketTypePriceByTheatre(theatre);
	}
	
	@Transactional
	public void removeTicketTypePrice(int id) {
		this.ticketTypePriceDAO.removeTicketTypePrice(id);
	}
        	
}
