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
import Services.AuthenticationService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoviePageController{
    
    @RequestMapping(value = "/movieinfopage")
    protected ModelAndView getMovieInfoPage(){
    
        ModelAndView modelandview = new ModelAndView("movieinfopage");        
        return modelandview;
    }
    
    @RequestMapping(value = "/movieinfopage", method = RequestMethod.POST)
    protected ModelAndView changeFavoriteState(HttpServletRequest request , HttpServletRequest response){
        //ServletContext sc = request.getServletContext();
        
        ModelAndView modelandview;
        
        request.setAttribute("favorite", 1);
        modelandview = new ModelAndView("movieinfopage");
      
        return modelandview;
    }
}

