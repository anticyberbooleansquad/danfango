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

import Model.Theatre;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class TheatreDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(TheatreDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addTheatre(Theatre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("Theatre saved successfully, Theatre Details="+u);
    }

    
    public void updateTheatre(Theatre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("Theatre updated successfully, Theatre Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<Theatre> listTheatres() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Theatre> theatresList = session.createQuery("from Theatre").list();
            for(Theatre u : theatresList){
                    logger.info("Theatre List::"+u);
            }
            return theatresList;
    }

    
    public Theatre getTheatreById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Theatre u = (Theatre) session.load(Theatre.class, new Integer(id));
            logger.info("Theatre loaded successfully, Theatre details="+u);
            return u;
    }

    
    public void removeTheatre(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Theatre u = (Theatre) session.load(Theatre.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("Theatre deleted successfully, person details="+u);
    }
    
    public List<Theatre> getTheatresLikeName(String name){
            Session session = this.sessionFactory.getCurrentSession();
            List theatres = session.createCriteria(Theatre.class).add(Restrictions.like("name", name)).list();
            return theatres;
    }

}
