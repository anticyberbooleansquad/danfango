/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Theatre;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Model.TicketTypePrice;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class TicketTypePriceDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(TicketTypePriceDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addTicketTypePrice(TicketTypePrice u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("TicketTypePrice saved successfully, TicketTypePrice Details="+u);
    }

    
    public void updateTicketTypePrice(TicketTypePrice u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("TicketTypePrice updated successfully, TicketTypePrice Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<TicketTypePrice> listTicketTypePrices() {
            Session session = this.sessionFactory.getCurrentSession();
            List<TicketTypePrice> ticketTypePricesList = session.createQuery("from TicketTypePrice").list();
            for(TicketTypePrice u : ticketTypePricesList){
                    logger.info("TicketTypePrice List::"+u);
            }
            return ticketTypePricesList;
    }

    
    public TicketTypePrice getTicketTypePriceById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            TicketTypePrice u = (TicketTypePrice) session.load(TicketTypePrice.class, new Integer(id));
            logger.info("TicketTypePrice loaded successfully, TicketTypePrice details="+u);
            return u;
    }
    
    public List<TicketTypePrice> getTicketTypePriceByTheatre(Theatre theatre) {
            Session session = this.sessionFactory.getCurrentSession();	
            List ticketTypePrices = session.createCriteria(TicketTypePrice.class).add(Restrictions.eq("theatre", theatre)).list();
            if (ticketTypePrices.isEmpty()){
                return null;
            }
            logger.info("TicketTypePrice loaded successfully, TicketTypePrice details="+ticketTypePrices);
            return ticketTypePrices;
    }
    
    public TicketTypePrice getTicketTypePriceByTheatreAndType(Theatre theatre, String type) {
            Session session = this.sessionFactory.getCurrentSession();	
            List ticketTypePrices = session.createCriteria(TicketTypePrice.class).add(Restrictions.eq("theatre", theatre)).add(Restrictions.eq("ticketType", type)).list();
            if (ticketTypePrices.isEmpty()){
                return null;
            }
            TicketTypePrice tp = (TicketTypePrice) ticketTypePrices.get(0);
            logger.info("TicketTypePrice loaded successfully, TicketTypePrice details="+ticketTypePrices);
            return tp;
    }

    
    public void removeTicketTypePrice(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            TicketTypePrice u = (TicketTypePrice) session.load(TicketTypePrice.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("TicketTypePrice deleted successfully, person details="+u);
    }
    

}
