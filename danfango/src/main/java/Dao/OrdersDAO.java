/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Model.Order;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class OrdersDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(OrdersDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addOrder(Order u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Order saved successfully, Order Details="+u);
    }

    
    public void updateOrder(Order u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Order updated successfully, Order Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Order> listOrders() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Order> ordersList = session.createQuery("from Order").list();
            for(Order u : ordersList){
                    logger.info("Order List::"+u);
            }
            return ordersList;
    }

    
    public Order getOrderById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Order u = (Order) session.load(Order.class, new Integer(id));
            logger.info("Order loaded successfully, Order details="+u);
            return u;
    }
    
    public List<Order> getOrdersByEmail(String email) {
            Session session = this.sessionFactory.getCurrentSession();	
            List orders = session.createCriteria(Order.class).add(Restrictions.eq("email", email)).list();
            if (orders.isEmpty()){
                return null;
            } 
            logger.info("Order loaded successfully, Order details="+orders);
            return orders;
    }
    
    public void removeOrder(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Order u = (Order) session.load(Order.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Order deleted successfully, person details="+u);
    }
    

}
