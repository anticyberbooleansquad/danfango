/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Configuration;

import Services.AuthenticationService;
import Services.UserService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author @author Konrad Juszkiewicz <kjuszkiewicz95 at gmail.com>
 */
@WebListener
public class MyServletContextListener implements ServletContextListener{
   
  
    @Override
    public void contextInitialized(ServletContextEvent event){}
    
    @Override
    public void contextDestroyed(ServletContextEvent event){}

}
