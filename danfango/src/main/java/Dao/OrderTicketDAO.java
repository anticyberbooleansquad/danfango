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

import Model.OrderTicket;
import Model.Ticket;
import Model.Order;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class OrderTicketDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(OrderTicketDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addOrderTicket(OrderTicket u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("OrderTicket saved successfully, OrderTicket Details="+u);
    }

    
    public void updateOrderTicket(OrderTicket u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("OrderTicket updated successfully, OrderTicket Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<OrderTicket> listOrderTickets() {
            Session session = this.sessionFactory.getCurrentSession();
            List<OrderTicket> orderTicketsList = session.createQuery("from OrderTicket").list();
            for(OrderTicket u : orderTicketsList){
                    logger.info("OrderTicket List::"+u);
            }
            return orderTicketsList;
    }

    
    public OrderTicket getOrderTicketById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            OrderTicket u = (OrderTicket) session.load(OrderTicket.class, new Integer(id));
            logger.info("OrderTicket loaded successfully, OrderTicket details="+u);
            return u;
    }
    
    public List<OrderTicket> getOrderTicketsByOrder(Order order) {
            Session session = this.sessionFactory.getCurrentSession();	
            List orderTickets = session.createCriteria(OrderTicket.class).add(Restrictions.eq("order", order)).list();
            if (orderTickets.isEmpty()){
                return null;
            }
            return orderTickets;
    }
    
    public OrderTicket getOrderTicketsByTicket(Ticket ticket) {
            Session session = this.sessionFactory.getCurrentSession();	
            List orderTickets = session.createCriteria(OrderTicket.class).add(Restrictions.eq("ticket", ticket)).list();
            if (orderTickets.isEmpty()){
                return null;
            }
            return (OrderTicket)orderTickets.get(0);
    }

    
    public void removeOrderTicket(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            OrderTicket u = (OrderTicket) session.load(OrderTicket.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("OrderTicket deleted successfully, person details="+u);
    }
    

}
