/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.OrderTicketDAO;
import Model.OrderTicket;

@Service
public class OrderTicketService {

	private OrderTicketDAO orderTicketDAO;

	public void setOrderTicketDAO(OrderTicketDAO orderTicketDAO) {
		this.orderTicketDAO = orderTicketDAO;
	}

	
	@Transactional
	public void addOrderTicket(OrderTicket u) {
		this.orderTicketDAO.addOrderTicket(u);
	}

	
	@Transactional
	public void updateOrderTicket(OrderTicket u) {
		this.orderTicketDAO.updateOrderTicket(u);
	}

	
	@Transactional
	public List<OrderTicket> listOrderTickets() {
		return this.orderTicketDAO.listOrderTickets();
	}

	
	@Transactional
	public OrderTicket getOrderTicketById(int id) {
		return this.orderTicketDAO.getOrderTicketById(id);
	}
        
        @Transactional
	public OrderTicket getOrderTicketByEmail(String email) {
		return this.orderTicketDAO.getOrderTicketByEmail(email);
	}
	
	@Transactional
	public void removeOrderTicket(int id) {
		this.orderTicketDAO.removeOrderTicket(id);
	}       	
}
