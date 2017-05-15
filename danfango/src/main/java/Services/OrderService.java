/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.OrderDAO;
import Model.LiveTickets;
import Model.Orders;

@Service
public class OrderService {

	private OrderDAO orderDAO;

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Transactional
	public void addOrder(Orders u) {
		this.orderDAO.addOrder(u);
	}

	
	@Transactional
	public void updateOrder(Orders u) {
		this.orderDAO.updateOrder(u);
	}

	
	@Transactional
	public List<Orders> listOrders() {
		return this.orderDAO.listOrders();
	}

	
	@Transactional
	public Orders getOrderById(int id) {
		return this.orderDAO.getOrderById(id);
	}
        
        @Transactional
	public Orders getOrderByEmail(String email) {
		return this.orderDAO.getOrderByEmail(email);
	}
	
	@Transactional
	public void removeOrder(int id) {
		this.orderDAO.removeOrder(id);
	}
        	
}
