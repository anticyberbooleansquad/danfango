/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.FavoriteMovie;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Model.FavoriteTheatre;
import Model.Theatre;
import Model.User;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class FavoriteTheatreDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(FavoriteTheatreDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addFavoriteTheatre(FavoriteTheatre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(u);
            logger.info("FavoriteTheatre saved successfully, FavoriteTheatre Details="+u);
    }

    
    public void updateFavoriteTheatre(FavoriteTheatre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("FavoriteTheatre updated successfully, FavoriteTheatre Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<FavoriteTheatre> listFavoriteTheatres() {
            Session session = this.sessionFactory.getCurrentSession();
            List<FavoriteTheatre> favoriteTheatresList = session.createQuery("from FavoriteTheatre").list();
            for(FavoriteTheatre u : favoriteTheatresList){
                    logger.info("FavoriteTheatre List::"+u);
            }
            return favoriteTheatresList;
    }

    
    public FavoriteTheatre getFavoriteTheatreById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            FavoriteTheatre u = (FavoriteTheatre) session.load(FavoriteTheatre.class, new Integer(id));
            logger.info("FavoriteTheatre loaded successfully, FavoriteTheatre details="+u);
            return u;
    }
    
    public List<FavoriteTheatre> getFavoriteTheatresByUser(User user) {
            Session session = this.sessionFactory.getCurrentSession();	
            List favoriteTheatres = session.createCriteria(FavoriteTheatre.class).add(Restrictions.eq("user", user)).list();
            if (favoriteTheatres.isEmpty()){
                return null;
            }
            logger.info("FavoriteMovie loaded successfully, FavoriteMovie details="+favoriteTheatres);
            return favoriteTheatres;
    }
    
    
    public FavoriteTheatre getFavoriteTheatreByUserAndTheatre(User user, Theatre theatre) {
            Session session = this.sessionFactory.getCurrentSession();	
            List favoriteTheatres = session.createCriteria(FavoriteTheatre.class).add(Restrictions.eq("theatre", theatre)).add(Restrictions.eq("user", user)).list();
            if (favoriteTheatres.isEmpty()){
                return null;
            }
            FavoriteTheatre u = (FavoriteTheatre) favoriteTheatres.get(0);
            logger.info("FavoriteTheatre loaded successfully, FavoriteTheatre details="+u);
            return u;
    }

    
    public void removeFavoriteTheatre(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            FavoriteTheatre u = (FavoriteTheatre) session.load(FavoriteTheatre.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("FavoriteTheatre deleted successfully, person details="+u);
    }
    

}
