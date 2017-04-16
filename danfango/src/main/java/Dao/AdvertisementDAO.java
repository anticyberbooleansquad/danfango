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

import Model.Advertisement;
/**
 *
 * @author charles
 */
@Repository
public class AdvertisementDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addAdvertisement(Advertisement a) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(a);
            logger.info("Advertisement saved successfully, Advertisement Details="+a);
    }

    
    public void updateAdvertisement(Advertisement a) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(a);
            logger.info("Advertisement updated successfully, Advertisement Details="+a);
    }

    @SuppressWarnings("unchecked")
    public List<Advertisement> listAdvertisements() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Advertisement> advertisementsList = session.createQuery("from Advertisement").list();
            for(Advertisement a : advertisementsList){
                    logger.info("Advertisement List::"+a);
            }
            return advertisementsList;
    }

    
    public Advertisement getAdvertisementById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Advertisement a = (Advertisement) session.load(Advertisement.class, new Integer(id));
            logger.info("Advertisement loaded successfully, Advertisement details="+a);
            return a;
    }

    
    public void removeAdvertisement(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Advertisement a = (Advertisement) session.load(Advertisement.class, new Integer(id));
            if(null != a){
                    session.delete(a);
            }
            logger.info("Advertisement deleted successfully, person details="+a);
    }

}
