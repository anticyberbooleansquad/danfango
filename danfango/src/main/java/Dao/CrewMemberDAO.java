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

import Model.CrewMember;
/**
 *
 * @author charles
 */
@Repository
public class CrewMemberDAO{
   
    private static final Logger logger = LoggerFactory.getLogger(CrewMemberDAO.class);
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
            this.sessionFactory = sf;
    }

    
    public void addCrewMember(CrewMember c) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(c);
            logger.info("CrewMember saved successfully, CrewMember Details="+c);
    }

    
    public void updateCrewMember(CrewMember c) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(c);
            logger.info("CrewMember updated successfully, CrewMember Details="+c);
    }

    @SuppressWarnings("unchecked")
    public List<CrewMember> listCrewMembers() {
            Session session = this.sessionFactory.getCurrentSession();
            List<CrewMember> crewMembersList = session.createQuery("from CrewMember").list();
            for(CrewMember c : crewMembersList){
                    logger.info("CrewMember List::"+c);
            }
            return crewMembersList;
    }

    
    public CrewMember getCrewMemberById(int id) {
            Session session = this.sessionFactory.getCurrentSession();		
            CrewMember c = (CrewMember) session.load(CrewMember.class, new Integer(id));
            logger.info("CrewMember loaded successfully, CrewMember details="+c);
            return c;
    }

    
    public void removeCrewMember(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            CrewMember c = (CrewMember) session.load(CrewMember.class, new Integer(id));
            if(null != c){
                    session.delete(c);
            }
            logger.info("CrewMember deleted successfully, person details="+c);
    }

}
