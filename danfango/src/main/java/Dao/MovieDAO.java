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

import Model.Movie;
/**
 *
 * @author charles
 */
@Repository
public class MovieDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addMovie(Movie m) {
            Session session = this.sessionFactory.getCurrentSession();
            session.merge(m);
            logger.info("Movie saved successfully, Movie Details="+m);
    }

    
    public void updateMovie(Movie m) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(m);
            logger.info("Movie updated successfully, Movie Details="+m);
    }

    @SuppressWarnings("unchecked")
    public List<Movie> listMovies() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Movie> moviesList = session.createQuery("from Movie").list();
            for(Movie m : moviesList){
                    logger.info("Movie List::"+m);
            }
            return moviesList;
    }

    
    public Movie getMovieById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Movie m = (Movie) session.load(Movie.class, new Integer(id));
            logger.info("Movie loaded successfully, Movie details="+m);
            return m;
    }
    
    public Movie getMovieByAgencyId(String agencyId) {
            Session session = this.sessionFactory.getCurrentSession();		
            Movie m = (Movie) session.load(Movie.class, agencyId);
            logger.info("Movie loaded successfully, Movie details="+m);
            return m;
    }

    
    public void removeMovie(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Movie m = (Movie) session.load(Movie.class, new Integer(id));
            if(null != m){
                    session.delete(m);
            }
            logger.info("Movie deleted successfully, person details="+m);
    }

}
