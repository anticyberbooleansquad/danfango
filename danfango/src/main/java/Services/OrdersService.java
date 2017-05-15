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
import Model.Orders;

@Service
public class OrdersService {

	private OrdersDAO ordersDAO;

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	
	@Transactional
	public void addOrder(Orders u) {
		this.ordersDAO.addOrder(u);
	}

	
	@Transactional
	public void updateOrder(Orders u) {
		this.ordersDAO.updateOrder(u);
	}

	
	@Transactional
	public List<Orders> listOrders() {
		return this.ordersDAO.listOrders();
	}

	
	@Transactional
	public Orders getOrderById(int id) {
		return this.ordersDAO.getOrderById(id);
	}
        
        @Transactional
	public Orders getOrderByEmail(String email) {
		return this.ordersDAO.getOrderByEmail(email);
	}
	
	@Transactional
	public void removeOrder(int id) {
		this.ordersDAO.removeOrder(id);
	}
        	
}
