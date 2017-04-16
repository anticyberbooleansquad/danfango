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

import Model.Review;
/**
 *
 * @author charles
 */
@Repository
public class ReviewDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(ReviewDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addReview(Review r) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(r);
            logger.info("Review saved successfully, Review Details="+r);
    }

    
    public void updateReview(Review r) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(r);
            logger.info("Review updated successfully, Review Details="+r);
    }

    @SuppressWarnings("unchecked")
    public List<Review> listReviews() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Review> reviewsList = session.createQuery("from Review").list();
            for(Review r : reviewsList){
                    logger.info("Review List::"+r);
            }
            return reviewsList;
    }

    
    public Review getReviewById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Review r = (Review) session.load(Review.class, new Integer(id));
            logger.info("Review loaded successfully, Review details="+r);
            return r;
    }

    
    public void removeReview(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Review r = (Review) session.load(Review.class, new Integer(id));
            if(null != r){
                    session.delete(r);
            }
            logger.info("Review deleted successfully, person details="+r);
    }

}
