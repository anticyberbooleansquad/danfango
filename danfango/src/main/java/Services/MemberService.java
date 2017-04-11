/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Model.User;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Konrad Juszkiewicz <kjuszkiewicz95 at gmail.com>
 */
@Service
public class MemberService {
     @Autowired 
    private ServletContext servletContext;
    
    public MemberService(){
    
    }
    
    public User getUserByEmail(String email){
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Legutko");
        return u;
    }
    
}
