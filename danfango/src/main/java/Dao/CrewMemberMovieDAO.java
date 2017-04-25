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

import Model.CrewMemberMovie;
import Model.CrewMember;
import Model.Movie;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class CrewMemberMovieDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(CrewMemberMovieDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addCrewMemberMovie(CrewMemberMovie u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("CrewMemberMovie saved successfully, CrewMemberMovie Details="+u);
    }

    
    public void updateCrewMemberMovie(CrewMemberMovie u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("CrewMemberMovie updated successfully, CrewMemberMovie Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<CrewMemberMovie> listCrewMemberMovies() {
            Session session = this.sessionFactory.getCurrentSession();
            List<CrewMemberMovie> crewMemberMoviesList = session.createQuery("from CrewMemberMovie").list();
            for(CrewMemberMovie u : crewMemberMoviesList){
                    logger.info("CrewMemberMovie List::"+u);
            }
            return crewMemberMoviesList;
    }

    
    public CrewMemberMovie getCrewMemberMovieById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            CrewMemberMovie u = (CrewMemberMovie) session.load(CrewMemberMovie.class, new Integer(id));
            logger.info("CrewMemberMovie loaded successfully, CrewMemberMovie details="+u);
            return u;
    }
    
    public List<CrewMemberMovie> getCrewMemberMovieByCrewMember(CrewMember crewMember) {
            Session session = this.sessionFactory.getCurrentSession();	
            List crewMemberMovies = session.createCriteria(CrewMemberMovie.class).add(Restrictions.eq("crewMember", crewMember)).list();
            logger.info("CrewMemberMovie loaded successfully, CrewMemberMovie details="+crewMemberMovies);
            return crewMemberMovies;
    }
    
    public List<CrewMemberMovie> getCrewMemberMovieByMovie(Movie movie) {
            Session session = this.sessionFactory.getCurrentSession();	
            List crewMemberMovies = session.createCriteria(CrewMemberMovie.class).add(Restrictions.eq("movie", movie)).list();
            logger.info("CrewMemberMovie loaded successfully, CrewMemberMovie details="+crewMemberMovies);
            return crewMemberMovies;
    }
    
    public CrewMemberMovie getCrewMemberMovieByJoe(Movie movie, CrewMember crewMember) {
            Session session = this.sessionFactory.getCurrentSession();	
            List crewMemberMovies = session.createCriteria(CrewMemberMovie.class).add(Restrictions.eq("movie", movie)).add(Restrictions.eq("crewMember", crewMember)).list();
            logger.info("CrewMemberMovie loaded successfully, CrewMemberMovie details="+crewMemberMovies);
            if(crewMemberMovies.isEmpty())
            {
                return null;
            }
            else
            {
                return (CrewMemberMovie) (crewMemberMovies.get(0));
            }
    }
   
    public void removeCrewMemberMovie(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            CrewMemberMovie u = (CrewMemberMovie) session.load(CrewMemberMovie.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("CrewMemberMovie deleted successfully, person details="+u);
    }
    

}
