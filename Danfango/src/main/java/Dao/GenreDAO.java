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

import Model.Genre;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author charles
 */
@Repository
public class GenreDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(GenreDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addGenre(Genre g) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(g);
            logger.info("Genre saved successfully, Genre Details="+g);
    }

    
    public void updateGenre(Genre g) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(g);
            logger.info("Genre updated successfully, Genre Details="+g);
    }

    @SuppressWarnings("unchecked")
    public List<Genre> listGenres() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Genre> genresList = session.createQuery("from Genre").list();
            for(Genre g : genresList){
                    logger.info("Genre List::"+g);
            }
            return genresList;
    }

    
    public Genre getGenreById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Genre g = (Genre) session.load(Genre.class, new Integer(id));
            logger.info("Genre loaded successfully, Genre details="+g);
            return g;
    }
    
    public Genre getGenreByName(String name) {
            Genre g;
            Session session = this.sessionFactory.getCurrentSession();	
            List genres = session.createCriteria(Genre.class).add(Restrictions.eq("name", name)).list();
            if (genres.isEmpty()){
                g = new Genre();
                g.setName(name);
                addGenre(g);
                g = getGenreByName(name);
            }
            else{
              g = (Genre) genres.get(0);
            }
            logger.info("Genre loaded successfully, Genre details="+g);
            return g;
    }

    
    public void removeGenre(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Genre g = (Genre) session.load(Genre.class, new Integer(id));
            if(null != g){
                    session.delete(g);
            }
            logger.info("Genre deleted successfully, person details="+g);
    }
    

}
