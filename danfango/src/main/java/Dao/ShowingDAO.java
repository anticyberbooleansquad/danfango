/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Movie;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Model.Showing;
import Model.Theatre;
import Model.TheatreRoom;
import java.sql.Timestamp;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class ShowingDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(ShowingDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addShowing(Showing u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Showing saved successfully, Showing Details="+u);
    }

    
    public void updateShowing(Showing u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Showing updated successfully, Showing Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Showing> listShowings() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Showing> showingsList = session.createQuery("from Showing").list();
            for(Showing u : showingsList){
                    logger.info("Showing List::"+u);
            }
            return showingsList;
    }

    
    public Showing getShowingById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Showing u = (Showing) session.load(Showing.class, new Integer(id));
            logger.info("Showing loaded successfully, Showing details="+u);
            return u;
    }

    public Showing getShowingByJoe(Movie movie, Theatre theatre, Timestamp time) {
            Session session = this.sessionFactory.getCurrentSession();		
            List showings = session.createCriteria(Showing.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("theatre", theatre)).add(Restrictions.eq("time", time)).list();
            if(showings.isEmpty())
            {
                return null;
            }
            else
            {
                return (Showing) showings.get(0);
            }
    }
    
    public void removeShowing(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Showing u = (Showing) session.load(Showing.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Showing deleted successfully, person details="+u);
    }

}
