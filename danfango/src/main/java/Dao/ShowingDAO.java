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
import Model.TheatreShowings;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author charles
 */
@Repository
public class ShowingDAO {

    private static final Logger logger = LoggerFactory.getLogger(ShowingDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void addShowing(Showing u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(u);
        logger.info("Showing saved successfully, Showing Details=" + u);
    }

    public void updateShowing(Showing u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);
        logger.info("Showing updated successfully, Showing Details=" + u);
    }

    @SuppressWarnings("unchecked")
    public List<Showing> listShowings() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Showing> showingsList = session.createQuery("from Showing").list();
        for (Showing u : showingsList) {
            logger.info("Showing List::" + u);
        }
        return showingsList;
    }

    public List<Integer> getMovieIdsByTheatre(Theatre theatre) {
        List<Showing> showings = getShowingByTheatre(theatre);
        HashSet<Integer> movieIdsHS = new HashSet<>();
        if (showings != null) {
            for (Showing s : showings) {
                int id = (s.getMovie().getId());
                movieIdsHS.add(id);
            }
        }
        List<Integer> movieIds = new ArrayList<>(movieIdsHS);
        return movieIds;
    }

    public Showing getShowingById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Showing u = (Showing) session.load(Showing.class, new Integer(id));
        logger.info("Showing loaded successfully, Showing details=" + u);
        return u;
    }

    public Showing getShowingByJoe(Movie movie, Theatre theatre, Timestamp time) {
        Session session = this.sessionFactory.getCurrentSession();
        List showings = session.createCriteria(Showing.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("theatre", theatre)).add(Restrictions.eq("time", time)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return (Showing) showings.get(0);
        }
    }

    public List<Showing> getShowingByTimestamp() {

        Timestamp today = new Timestamp(System.currentTimeMillis());
        Session session = this.sessionFactory.getCurrentSession();
        List showings = session.createCriteria(Showing.class).add(Restrictions.ge("time", today)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return showings;
        }
    }

    public List<Showing> getShowingByMovieAndTheatre(Movie movie, Theatre theatre) {
        Session session = this.sessionFactory.getCurrentSession();
        List showings = session.createCriteria(Showing.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("theatre", theatre)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return showings;
        }
    }

    public List<Showing> getShowingByMovieAndTheatreAndTime(Movie movie, Theatre theatre, Timestamp date) {
        Session session = this.sessionFactory.getCurrentSession();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Timestamp tomorrow = new Timestamp(cal.getTime().getTime());
        
        List showings = session.createCriteria(Showing.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("theatre", theatre)).add(Restrictions.ge("time", date)).add(Restrictions.le("time", tomorrow)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return showings;
        }
    }

    public List<Showing> getShowingByMovie(Movie movie) {
        Session session = this.sessionFactory.getCurrentSession();
        List showings = session.createCriteria(Showing.class).add(Restrictions.eq("movie", movie)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return showings;
        }
    }

    public List<Showing> getShowingByTheatre(Theatre theatre) {
        Session session = this.sessionFactory.getCurrentSession();
        List showings = session.createCriteria(Showing.class).add(Restrictions.eq("theatre", theatre)).list();
        if (showings.isEmpty()) {
            return null;
        } else {
            return showings;
        }
    }

    public void removeShowing(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Showing u = (Showing) session.load(Showing.class, new Integer(id));
        if (null != u) {
            session.delete(u);
        }
        logger.info("Showing deleted successfully, person details=" + u);
    }

}
