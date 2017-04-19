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
public class MyAccountController{
    
    
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    
    @RequestMapping(value = "/userpage")
    protected ModelAndView getSearchResultPage(){
    
        ModelAndView modelandview = new ModelAndView("userpage");        
        return modelandview;
    }
    
    @RequestMapping(value = "/changeemail", method = RequestMethod.POST)
    protected ModelAndView changeEmail(@RequestParam("email") String email, HttpServletRequest request){
        ModelAndView modelandview;
        
        if (userService.getUserByEmail(email) != null){
            request.setAttribute("UsedEmail", "The email You have selected is already attached to an account");
            modelandview = new ModelAndView("userpage");
            return modelandview;
        }
        else{
            HttpSession session = request.getSession();

            User u = (User) session.getAttribute("user");
            u.setEmail(email);
            userService.updateUser(u);
            
            session.setAttribute("user", u);   
            modelandview = new ModelAndView("userpage");
            return modelandview;
        }
    }
    
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    protected ModelAndView ChangePassword(@RequestParam("password") String password, HttpServletRequest request){
        ModelAndView modelandview;
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        u.setPassword(authenticationService.hash(password));
        userService.updateUser(u);    
        session.setAttribute("user", u);   
        modelandview = new ModelAndView("userpage");
        return modelandview;
        
    }
    
    
}
