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

import Model.Ticket;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class TicketDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(TicketDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addTicket(Ticket u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Ticket saved successfully, Ticket Details="+u);
    }

    
    public void updateTicket(Ticket u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Ticket updated successfully, Ticket Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> listTickets() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Ticket> ticketsList = session.createQuery("from Ticket").list();
            for(Ticket u : ticketsList){
                    logger.info("Ticket List::"+u);
            }
            return ticketsList;
    }

    
    public Ticket getTicketById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Ticket u = (Ticket) session.load(Ticket.class, new Integer(id));
            logger.info("Ticket loaded successfully, Ticket details="+u);
            return u;
    }
    
    public Ticket getTicketByEmail(String email) {
            Session session = this.sessionFactory.getCurrentSession();	
            List tickets = session.createCriteria(Ticket.class).add(Restrictions.eq("email", email)).list();
            if (tickets.isEmpty()){
                return null;
            }
            Ticket u = (Ticket) tickets.get(0);
            logger.info("Ticket loaded successfully, Ticket details="+u);
            return u;
    }

    
    public void removeTicket(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Ticket u = (Ticket) session.load(Ticket.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Ticket deleted successfully, person details="+u);
    }
    

}
