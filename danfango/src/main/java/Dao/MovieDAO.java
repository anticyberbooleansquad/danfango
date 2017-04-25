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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author charles
 */
@Repository
public class MovieDAO {

    private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void addMovie(Movie m) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(m);
        logger.info("Movie saved successfully, Movie Details=" + m);
    }

    public void updateMovie(Movie m) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(m);
        logger.info("Movie updated successfully, Movie Details=" + m);
    }

    @SuppressWarnings("unchecked")
    public List<Movie> listMovies() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Movie> moviesList = session.createQuery("from Movie").list();
        for (Movie m : moviesList) {
            logger.info("Movie List::" + m);
        }
        return moviesList;
    }

    public Movie getMovieById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie m = (Movie) session.load(Movie.class, new Integer(id));
        logger.info("Movie loaded successfully, Movie details=" + m);
        return m;
    }

    public Movie getMovieByAgencyMovieId(String agencyMovieId) {
        Session session = this.sessionFactory.getCurrentSession();
        List movies = session.createCriteria(Movie.class).add(Restrictions.eq("imdbID", agencyMovieId)).list();
        if (movies.isEmpty()) {
            return null;
        }
        Movie m = (Movie) movies.get(0);
        logger.info("Movie loaded successfully, Movie details=" + m);
        return m;
    }

    public List<Movie> getMoviesOpeningThisWeek() {
        Session session = this.sessionFactory.getCurrentSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date date = cal.getTime();
        Timestamp today = new Timestamp(System.currentTimeMillis());
        Timestamp nextWeek = new Timestamp(date.getTime());
        
        System.out.println("TODAY: "+ today);
        System.out.println("NextWeeK: "+ nextWeek);


        List movies = session.createCriteria(Movie.class).add(Restrictions.between("releaseDate", today, nextWeek)).list();
        if (movies.isEmpty()) {
            return null;
        }
        logger.info("Opening This Week loaded successfully, Movie details=" + movies);
        return movies;
    }
    
    public List<Movie> getMoviesNowPlaying() {
        Session session = this.sessionFactory.getCurrentSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -50);
        Date date = cal.getTime();
        Timestamp today = new Timestamp(System.currentTimeMillis());
        Timestamp lastMonth = new Timestamp(date.getTime());

        List movies = session.createCriteria(Movie.class).add(Restrictions.between("releaseDate", lastMonth, today)).list();
        if (movies.isEmpty()) {
            return null;
        }
        
        logger.info("Now Playing loaded successfully, Movie details=" + movies);
        return movies;
    }
    
    public List<Movie> getMoviesComingSoon() {
        Session session = this.sessionFactory.getCurrentSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 240);
        Date date = cal.getTime();
        Timestamp today = new Timestamp(System.currentTimeMillis());
        Timestamp comingSoon = new Timestamp(date.getTime());

        List movies = session.createCriteria(Movie.class).add(Restrictions.between("releaseDate", today, comingSoon)).list();
        if (movies.isEmpty()) {
            return null;
        }
        logger.info("Coming Soon successfully, Movie details=" + movies);
        return movies;
    }

    public void removeMovie(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie m = (Movie) session.load(Movie.class, new Integer(id));
        if (null != m) {
            session.delete(m);
        }
        logger.info("Movie deleted successfully, person details=" + m);
    }
    
    public List<Movie> getMoviesLikeTitle(String title){
        Session session = this.sessionFactory.getCurrentSession();
        List movies = session.createCriteria(Movie.class).add(Restrictions.like("title", title)).list();
        return movies;
    }

}
