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
import Model.CrewMemberMovie;
import Model.FavoriteMovie;
import Model.Movie;
import Model.User;
import Services.AuthenticationService;
import Services.CrewMemberMovieService;
import Services.FavoriteMovieService;
import Services.MovieService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoviePageController{
    
    @Autowired
    MovieService movieService;
    @Autowired
    CrewMemberMovieService crewMemberMovieService;
    @Autowired
    FavoriteMovieService favoriteMovieService;
    
    @RequestMapping(value = "/movieinfopage/{movieId}")
    protected ModelAndView getMovieInfoPage(@PathVariable(value="movieId") int id, HttpServletRequest request){
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        Movie movie = movieService.getMovieById(id);
        User user = (User)session.getAttribute("user");
        
        FavoriteMovie fav2 = favoriteMovieService.getFavoriteMovieByUserAndMovie(user, movie);
        
        if(fav2 != null)
        {
            request.setAttribute("favoriteState", true);
        }
        else
        {
            request.setAttribute("favoriteState", false);
        }
        
        request.setAttribute("movie", movie);
       
        List<CrewMemberMovie> crewMemberMovie = crewMemberMovieService.getCrewMemberMovieByMovie(movie);
        request.setAttribute("crewMemberMovie",crewMemberMovie);
        
        ModelAndView modelandview = new ModelAndView("movieinfopage");        
        return modelandview;
    }
    
//    @RequestMapping(value = "/movieinfopage", method = RequestMethod.POST)
//    protected ModelAndView changeFavoriteState(HttpServletRequest request , HttpServletRequest response){
//        //ServletContext sc = request.getServletContext();
//        
//        ModelAndView modelandview;
//        
//        request.setAttribute("favorite", 1);
//        modelandview = new ModelAndView("movieinfopage");
//      
//        return modelandview;
//    }
    
    @RequestMapping(value = "/changeFavorite", method = RequestMethod.POST)
    protected String changeFavoriteState(HttpServletRequest request){
        System.out.println("fuck");
        return "success";
    }
}

