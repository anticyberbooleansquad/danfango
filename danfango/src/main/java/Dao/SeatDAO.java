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

import Model.Seat;
import Model.TheatreRoom;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class SeatDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(SeatDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addSeat(Seat u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Seat saved successfully, Seat Details="+u);
    }

    
    public void updateSeat(Seat u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Seat updated successfully, Seat Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Seat> listSeats() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Seat> seatsList = session.createQuery("from Seat").list();
            for(Seat u : seatsList){
                    logger.info("Seat List::"+u);
            }
            return seatsList;
    }

    
    public Seat getSeatById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Seat u = (Seat) session.load(Seat.class, new Integer(id));
            logger.info("Seat loaded successfully, Seat details="+u);
            return u;
    }
    
    public List<Seat> getSeatsByTheatreRoom(TheatreRoom theatreRoom) {
            Session session = this.sessionFactory.getCurrentSession();	
            List seats = session.createCriteria(Seat.class).add(Restrictions.eq("theatreRoom", theatreRoom)).list();
            if (seats.isEmpty()){
                return null;
            }
            logger.info("Seat loaded successfully, Seat details="+seats);
            return seats;
    }

    
    public void removeSeat(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Seat u = (Seat) session.load(Seat.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Seat deleted successfully, person details="+u);
    }
    

}
