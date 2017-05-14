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
import Model.Genre;
import Model.Movie;
import Model.MovieGenre;
import Services.GenreService;
import Services.MovieGenreService;
import Services.MovieService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComingSoonController{
    
    @Autowired
    MovieService movieService;
    @Autowired
    GenreService genreService;
    @Autowired
    MovieGenreService movieGenreService;
    
    
    @RequestMapping(value = "/comingsoon")
    protected ModelAndView getComingSoonPage(HttpServletRequest request){
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        request.setAttribute("path", "/danfango/");

        List<Movie> comingSoon = movieService.getMoviesComingSoon();

        request.setAttribute("comingSoon", comingSoon);

        ModelAndView modelandview = new ModelAndView("comingsoon");        
        return modelandview;
    }
    
    @RequestMapping(value = "/comingsoon/{genre}")
    protected ModelAndView getMovieGenrePage(HttpServletRequest request, @PathVariable(value="genre") String genre){  
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        request.setAttribute("path", "/danfango/");
        
        
        List<Movie> movs = new ArrayList<>();
        List<Movie> comingSoon = movieService.getMoviesComingSoon();
        // have list of movies go through check genre if genre does not match then remove it from the list
        Genre g = genreService.getGenreByName(genre);
        
        for(Movie movie : comingSoon){
            if (movieGenreService.getMovieGenresByGenreAndMovie(g, movie) != null){
                movs.add(movie);
            }
        }
        
        request.setAttribute("comingSoon",movs);
        
        ModelAndView modelandview = new ModelAndView("comingsoon");        
        return modelandview;
    }
    
    
}
