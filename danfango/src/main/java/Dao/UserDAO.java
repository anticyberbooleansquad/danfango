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

import Model.User;
/**
 *
 * @author charles
 */
@Repository
public class UserDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addUser(User u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(u);
            logger.info("User saved successfully, User Details="+u);
    }

    
    public void updateUser(User u) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(u);
            logger.info("User updated successfully, User Details="+u);
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
            Session session = this.sessionFactory.getCurrentSession();
            List<User> personsList = session.createQuery("from User").list();
            for(User p : personsList){
                    logger.info("User List::"+p);
            }
            return personsList;
    }

    
    public User getUserById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            User u = (User) session.load(User.class, new Integer(id));
            logger.info("User loaded successfully, User details="+u);
            return u;
    }

    
    public void removeUser(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            User u = (User) session.load(User.class, new Integer(id));
            if(null != u){
                    session.delete(u);
            }
            logger.info("User deleted successfully, person details="+u);
    }

}
