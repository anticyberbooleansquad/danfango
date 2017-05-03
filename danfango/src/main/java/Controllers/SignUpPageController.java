/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author johnlegutko
 */
import Model.User;
import Services.AuthenticationService;
import Services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpPageController{
    
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    
    @RequestMapping(value = "/signuppage")
    protected ModelAndView getSignUpPage(HttpServletRequest request){
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
    
        ModelAndView modelandview = new ModelAndView("signuppage");        
        return modelandview;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView register(@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname, HttpServletRequest request){
        ModelAndView modelandview;
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        if (userService.getUserByEmail(email) != null){
            request.setAttribute("UsedEmail", "The email You have selected is already attached to an account");
            modelandview = new ModelAndView("signuppage");
            return modelandview;
        }
        else{
            User u = new User(); 
            u.setFirstName(firstname);
            u.setLastName(lastname);
            u.setEmail(email);
            u.setPassword(authenticationService.hash(password));
            userService.addUser(u);

            
            HttpSession session = request.getSession();
            session.setAttribute("user", u);   
            modelandview = new ModelAndView("index");
            return modelandview;
        }
    }
    
    
}

