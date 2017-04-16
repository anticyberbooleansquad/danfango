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

import Model.Agency;
/**
 *
 * @author charles
 */
@Repository
public class AgencyDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(Agency.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addAgency(Agency a) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(a);
            logger.info("Agency saved successfully, Agency Details="+a);
    }

    
    public void updateAgency(Agency a) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(a);
            logger.info("Agency updated successfully, Agency Details="+a);
    }

    @SuppressWarnings("unchecked")
    public List<Agency> listAgencys() {
            Session session = this.sessionFactory.getCurrentSession();
            List<Agency> agencysList = session.createQuery("from Agency").list();
            for(Agency a : agencysList){
                    logger.info("Agency List::"+a);
            }
            return agencysList;
    }

    
    public Agency getAgencyById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            Agency a = (Agency) session.load(Agency.class, new Integer(id));
            logger.info("Agency loaded successfully, Agency details="+a);
            return a;
    }

    
    public void removeAgency(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            Agency a = (Agency) session.load(Agency.class, new Integer(id));
            if(null != a){
                    session.delete(a);
            }
            logger.info("Agency deleted successfully, person details="+a);
    }

}
