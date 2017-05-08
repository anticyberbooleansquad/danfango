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

import Model.SeatReserved;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class SeatReservedDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(SeatReservedDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addSeatReserved(SeatReserved u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("SeatReserved saved successfully, SeatReserved Details="+u);
    }

    
    public void updateSeatReserved(SeatReserved u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("SeatReserved updated successfully, SeatReserved Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<SeatReserved> listSeatReserveds() {
            Session session = this.sessionFactory.getCurrentSession();
            List<SeatReserved> seatReservedsList = session.createQuery("from SeatReserved").list();
            for(SeatReserved u : seatReservedsList){
                    logger.info("SeatReserved List::"+u);
            }
            return seatReservedsList;
    }

    
    public SeatReserved getSeatReservedById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            SeatReserved u = (SeatReserved) session.load(SeatReserved.class, new Integer(id));
            logger.info("SeatReserved loaded successfully, SeatReserved details="+u);
            return u;
    }
    
    public SeatReserved getSeatReservedByEmail(String email) {
            Session session = this.sessionFactory.getCurrentSession();	
            List seatReserveds = session.createCriteria(SeatReserved.class).add(Restrictions.eq("email", email)).list();
            if (seatReserveds.isEmpty()){
                return null;
            }
            SeatReserved u = (SeatReserved) seatReserveds.get(0);
            logger.info("SeatReserved loaded successfully, SeatReserved details="+u);
            return u;
    }

    
    public void removeSeatReserved(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            SeatReserved u = (SeatReserved) session.load(SeatReserved.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("SeatReserved deleted successfully, person details="+u);
    }
    

}
