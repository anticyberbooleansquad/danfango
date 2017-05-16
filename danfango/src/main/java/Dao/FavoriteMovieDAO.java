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

import Model.FavoriteMovie;
import Model.Movie;
import Model.User;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class FavoriteMovieDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(FavoriteMovieDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addFavoriteMovie(FavoriteMovie u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(u);
            logger.info("FavoriteMovie saved successfully, FavoriteMovie Details="+u);
    }

    
    public void updateFavoriteMovie(FavoriteMovie u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("FavoriteMovie updated successfully, FavoriteMovie Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<FavoriteMovie> listFavoriteMovies() {
            Session session = this.sessionFactory.getCurrentSession();
            List<FavoriteMovie> favoriteMoviesList = session.createQuery("from FavoriteMovie").list();
            for(FavoriteMovie u : favoriteMoviesList){
                    logger.info("FavoriteMovie List::"+u);
            }
            return favoriteMoviesList;
    }

    
    public FavoriteMovie getFavoriteMovieById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            FavoriteMovie u = (FavoriteMovie) session.load(FavoriteMovie.class, new Integer(id));
            logger.info("FavoriteMovie loaded successfully, FavoriteMovie details="+u);
            return u;
    }
    
    public List<FavoriteMovie> getFavoriteMoviesByUser(User user) {
            Session session = this.sessionFactory.getCurrentSession();	
            List favoriteMovies = session.createCriteria(FavoriteMovie.class).add(Restrictions.eq("user", user)).list();
            if (favoriteMovies.isEmpty()){
                return null;
            }
            logger.info("FavoriteMovie loaded successfully, FavoriteMovie details="+favoriteMovies);
            return favoriteMovies;
    }
    
    public FavoriteMovie getFavoriteMovieByUserAndMovie(User user, Movie movie) {
            Session session = this.sessionFactory.getCurrentSession();	
            List favoriteMovies = session.createCriteria(FavoriteMovie.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("user", user)).list();
            if (favoriteMovies.isEmpty()){
                return null;
            }
            FavoriteMovie u = (FavoriteMovie) favoriteMovies.get(0);
            logger.info("FavoriteMovie loaded successfully, FavoriteMovie details="+u);
            return u;
    }

    
    public void removeFavoriteMovie(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            FavoriteMovie u = (FavoriteMovie) session.load(FavoriteMovie.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("FavoriteMovie deleted successfully, person details="+u);
    }
    

}
