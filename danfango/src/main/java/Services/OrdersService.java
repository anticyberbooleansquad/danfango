/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.OrdersDAO;
import Model.LiveTickets;
import Model.Order;

@Service
public class OrdersService {

	private OrdersDAO ordersDAO;

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	
	@Transactional
	public void addOrder(Order u) {
		this.ordersDAO.addOrder(u);
	}

	
	@Transactional
	public void updateOrder(Order u) {
		this.ordersDAO.updateOrder(u);
	}

	
	@Transactional
	public List<Order> listOrders() {
		return this.ordersDAO.listOrders();
	}

	
	@Transactional
	public Order getOrderById(int id) {
		return this.ordersDAO.getOrderById(id);
	}
        
        @Transactional
	public List<Order> getOrdersByEmail(String email) {
		return this.ordersDAO.getOrdersByEmail(email);
	}
	
	@Transactional
	public void removeOrder(int id) {
		this.ordersDAO.removeOrder(id);
	}
        	
}
