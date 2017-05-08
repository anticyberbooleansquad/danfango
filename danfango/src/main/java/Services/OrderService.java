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
import Model.Order;

@Service
public class OrderService {

	private OrderDAO orderDAO;

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	
	@Transactional
	public void addOrder(Order u) {
		this.orderDAO.addOrder(u);
	}

	
	@Transactional
	public void updateOrder(Order u) {
		this.orderDAO.updateOrder(u);
	}

	
	@Transactional
	public List<Order> listOrders() {
		return this.orderDAO.listOrders();
	}

	
	@Transactional
	public Order getOrderById(int id) {
		return this.orderDAO.getOrderById(id);
	}
        
        @Transactional
	public Order getOrderByEmail(String email) {
		return this.orderDAO.getOrderByEmail(email);
	}
	
	@Transactional
	public void removeOrder(int id) {
		this.orderDAO.removeOrder(id);
	}
        	
}
