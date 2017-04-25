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

import Model.TheatreRoom;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class TheatreRoomDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(TheatreRoomDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addTheatreRoom(TheatreRoom u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("TheatreRoom saved successfully, TheatreRoom Details="+u);
    }

    
    public void updateTheatreRoom(TheatreRoom u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("TheatreRoom updated successfully, TheatreRoom Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<TheatreRoom> listTheatreRooms() {
            Session session = this.sessionFactory.getCurrentSession();
            List<TheatreRoom> theatreRoomsList = session.createQuery("from TheatreRoom").list();
            for(TheatreRoom u : theatreRoomsList){
                    logger.info("TheatreRoom List::"+u);
            }
            return theatreRoomsList;
    }

    
    public TheatreRoom getTheatreRoomById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            TheatreRoom u = (TheatreRoom) session.load(TheatreRoom.class, new Integer(id));
            logger.info("TheatreRoom loaded successfully, TheatreRoom details="+u);
            return u;
    }
    
    public TheatreRoom getTheatreRoomByTheatre(Theatre theatre) {
            Session session = this.sessionFactory.getCurrentSession();		
            List theatreRooms = session.createCriteria(TheatreRoom.class).add(Restrictions.eq("theatre", theatre)).list();
            if(theatreRooms.isEmpty())
            {
                return null;
            }
            else
            {
                return (TheatreRoom) theatreRooms.get(0);
            }
    }

    
    public void removeTheatreRoom(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            TheatreRoom u = (TheatreRoom) session.load(TheatreRoom.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("TheatreRoom deleted successfully, person details="+u);
    }

}
