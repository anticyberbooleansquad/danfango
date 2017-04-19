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
import Model.Movie;
import Services.MovieService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComingSoonController{
    
    @Autowired
    MovieService movieService;
    
    @RequestMapping(value = "/comingsoon")
    protected ModelAndView getComingSoonPage(HttpServletRequest request){
    
        
        List<Movie> comingSoon = movieService.getMoviesComingSoon();

        request.setAttribute("comingSoon", comingSoon);

        ModelAndView modelandview = new ModelAndView("comingsoon");        
        return modelandview;
    }
    
    
}
