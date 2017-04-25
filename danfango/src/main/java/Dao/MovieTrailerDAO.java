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

import Model.MovieTrailer;
import Model.CrewMember;
import Model.Movie;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class MovieTrailerDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(MovieTrailerDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addMovieTrailer(MovieTrailer u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("MovieTrailer saved successfully, MovieTrailer Details="+u);
    }

    
    public void updateMovieTrailer(MovieTrailer u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("MovieTrailer updated successfully, MovieTrailer Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<MovieTrailer> listMovieTrailers() {
            Session session = this.sessionFactory.getCurrentSession();
            List<MovieTrailer> movieTrailersList = session.createQuery("from MovieTrailer").list();
            for(MovieTrailer u : movieTrailersList){
                    logger.info("MovieTrailer List::"+u);
            }
            return movieTrailersList;
    }

    
    public MovieTrailer getMovieTrailerById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            MovieTrailer u = (MovieTrailer) session.load(MovieTrailer.class, new Integer(id));
            logger.info("MovieTrailer loaded successfully, MovieTrailer details="+u);
            return u;
    }
    
    public List<MovieTrailer> getMovieTrailerByMovie(Movie movie) {
            Session session = this.sessionFactory.getCurrentSession();	
            List movieTrailers = session.createCriteria(MovieTrailer.class).add(Restrictions.eq("movie", movie)).list();
            logger.info("MovieTrailer loaded successfully, MovieTrailer details="+movieTrailers);
            return movieTrailers;
    }
    
    public MovieTrailer getMovieTrailerByAgencyID(String agencyId) {
        Session session = this.sessionFactory.getCurrentSession();
        List movieTrailers = session.createCriteria(Movie.class).add(Restrictions.eq("agencyId", agencyId)).list();
        if (movieTrailers.isEmpty()) {
            return null;
        }
        MovieTrailer m = (MovieTrailer) movieTrailers.get(0);
        logger.info("Movie loaded successfully, Movie details=" + m);
        return m;
    }
   
    public void removeMovieTrailer(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            MovieTrailer u = (MovieTrailer) session.load(MovieTrailer.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("MovieTrailer deleted successfully, person details="+u);
    }
    

}
