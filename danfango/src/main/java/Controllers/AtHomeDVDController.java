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
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
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
public class AtHomeDVDController{
    
    @Autowired
    MovieService movieService;
    
    @RequestMapping(value = "/athomedvd")
    protected ModelAndView getDVDPage(HttpServletRequest request){
    
        List<Movie> dvds = Lists.reverse(movieService.getOldMovies());
         
        request.setAttribute("dvds", dvds);
        
        ModelAndView modelandview = new ModelAndView("athomedvd");        
        return modelandview;
    }
    
    
}
