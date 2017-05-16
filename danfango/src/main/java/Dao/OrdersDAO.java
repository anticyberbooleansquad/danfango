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

import Model.Orders;
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

    
    public void addOrder(Orders u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Order saved successfully, Order Details="+u);
    }

    
    public void updateOrder(Orders u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Order updated successfully, Order Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Orders> listOrders() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Orders> ordersList = session.createQuery("from Order").list();
            for(Orders u : ordersList){
                    logger.info("Order List::"+u);
            }
            return ordersList;
    }

    
    public Orders getOrderById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Orders u = (Orders) session.load(Orders.class, new Integer(id));
            logger.info("Order loaded successfully, Order details="+u);
            return u;
    }
    
    public List<Orders> getOrdersByEmail(String email) {
            Session session = this.sessionFactory.getCurrentSession();	
            List orders = session.createCriteria(Orders.class).add(Restrictions.eq("email", email)).list();
            if (orders.isEmpty()){
                return null;
            } 
            logger.info("Order loaded successfully, Order details="+orders);
            return orders;
    }
    
    public void removeOrder(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Orders u = (Orders) session.load(Orders.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Order deleted successfully, person details="+u);
    }
    

}
