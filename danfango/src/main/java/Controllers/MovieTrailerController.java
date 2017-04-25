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
import Model.MovieTrailer;
import Services.MovieService;
import Services.MovieTrailerService;
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
public class MovieTrailerController{
    @Autowired
    MovieService movieService;
    @Autowired
    MovieTrailerService movieTrailerService;
    
    @RequestMapping(value = "/movietrailerpage/{movieId}")
    protected ModelAndView getMovieTrailerPage(@PathVariable(value="movieId") int id, HttpServletRequest request){
        
        Movie movie = movieService.getMovieById(id);
        request.setAttribute("movie", movie);
        
        List<MovieTrailer> trailers = movieTrailerService.getMovieTrailerByMovie(movie);
        request.setAttribute("trailers", trailers);
    
        ModelAndView modelandview = new ModelAndView("movietrailerpage");        
        return modelandview;
    }
    
    
}
