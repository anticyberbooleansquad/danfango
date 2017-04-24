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
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author charles
 */
@Repository
public class TheatreDAO {

    private static final Logger logger = LoggerFactory.getLogger(TheatreDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void addTheatre(Theatre u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
        logger.info("Theatre saved successfully, Theatre Details=" + u);
    }

    public void updateTheatre(Theatre u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);
        logger.info("Theatre updated successfully, Theatre Details=" + u);
    }

    @SuppressWarnings("unchecked")
    public List<Theatre> listTheatres() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Theatre> theatresList = session.createQuery("from Theatre").list();
        for (Theatre u : theatresList) {
            logger.info("Theatre List::" + u);
        }
        return theatresList;
    }

    public Theatre getTheatreById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Theatre u = (Theatre) session.load(Theatre.class, new Integer(id));
        logger.info("Theatre loaded successfully, Theatre details=" + u);
        return u;
    }

    public void removeTheatre(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Theatre u = (Theatre) session.load(Theatre.class, new Integer(id));
        if (null != u) {
            session.delete(u);
        }
        logger.info("Theatre deleted successfully, person details=" + u);
    }

    public List<Theatre> getTheatresLikeName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        List theatres = session.createCriteria(Theatre.class).add(Restrictions.like("name", name)).list();
        return theatres;
    }

    public List<Theatre> getTheatresInZipList(ArrayList<String> zipcodes) {
        Session session = this.sessionFactory.getCurrentSession();
        List theatres = session.createCriteria(Theatre.class).add(Restrictions.in("zip", zipcodes)).list();
        return theatres;
    }

    public List<Theatre> getTheatresByState(String state) {
        Session session = this.sessionFactory.getCurrentSession();
        List theatres = session.createCriteria(Theatre.class).add(Restrictions.eq("state", state)).list();
        return theatres;
    }
    
    public List<Theatre> getTheatresLikeCityByState(String city, String state){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Theatre.class);
        criteria.add(Restrictions.like("city", city));
        criteria.add(Restrictions.eq("state", state));
        List theatres = criteria.list();
        return theatres;
    }
    
    public List<Theatre> getTheatresLikeCityAndLikeState(String city, String state){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Theatre.class);
        criteria.add(Restrictions.like("city", city));
        // AND (
        criteria.add(Restrictions.like("state", state));
        // OR 
        // criteria.add(Restrictions.like("full_State", state));
        
        List theatres = criteria.list();
        return theatres;
    }
    
    public List<Theatre> getTheatresLikeCity(String city){
        Session session = this.sessionFactory.getCurrentSession();
        List theatres = session.createCriteria(Theatre.class).add(Restrictions.like("city", city)).list();
        return theatres;
    }
    
     public List<Theatre> getTheatresLikeState(String state){
        Session session = this.sessionFactory.getCurrentSession();
        List theatres = session.createCriteria(Theatre.class).add(Restrictions.like("state", state)).list();
        return theatres;
    }

}
