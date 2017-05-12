/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Genre;
import Model.Movie;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import Model.MovieGenre;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class MovieGenreDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(MovieGenreDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addMovieGenre(MovieGenre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("MovieGenre saved successfully, MovieGenre Details="+u);
    }

    
    public void updateMovieGenre(MovieGenre u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("MovieGenre updated successfully, MovieGenre Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<MovieGenre> listMovieGenres() {
            Session session = this.sessionFactory.getCurrentSession();
            List<MovieGenre> movieGenresList = session.createQuery("from MovieGenre").list();
            for(MovieGenre u : movieGenresList){
                    logger.info("MovieGenre List::"+u);
            }
            return movieGenresList;
    }

    
    public MovieGenre getMovieGenreById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            MovieGenre u = (MovieGenre) session.load(MovieGenre.class, new Integer(id));
            logger.info("MovieGenre loaded successfully, MovieGenre details="+u);
            return u;
    }
    
    public List<MovieGenre> getMovieGenresByMovie(Movie movie) {
            Session session = this.sessionFactory.getCurrentSession();	
            List movieGenres = session.createCriteria(MovieGenre.class).add(Restrictions.eq("movie", movie)).list();
            if (movieGenres.isEmpty()){
                return null;
            }
            logger.info("MovieGenre loaded successfully, MovieGenre details="+movieGenres);
            return movieGenres;
    }
    
    public List<MovieGenre> getMovieGenresByGenre(Genre genre) {
            Session session = this.sessionFactory.getCurrentSession();	
            List movieGenres = session.createCriteria(MovieGenre.class).add(Restrictions.eq("genre", genre)).list();
            if (movieGenres.isEmpty()){
                return null;
            }
            logger.info("MovieGenre loaded successfully, MovieGenre details="+movieGenres);
            return movieGenres;
    }
    
    public MovieGenre getMovieGenresByGenreAndMovie(Genre genre,Movie movie) {
            Session session = this.sessionFactory.getCurrentSession();	
            List movieGenres = session.createCriteria(MovieGenre.class).add(Restrictions.eq("genre", genre)).add(Restrictions.eq("movie", movie)).list();
            if (movieGenres.isEmpty()){
                return null;
            }
            logger.info("MovieGenre loaded successfully, MovieGenre details="+movieGenres);
            return (MovieGenre) movieGenres.get(0);
    }

    
    public void removeMovieGenre(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            MovieGenre u = (MovieGenre) session.load(MovieGenre.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("MovieGenre deleted successfully, person details="+u);
    }
    

}
