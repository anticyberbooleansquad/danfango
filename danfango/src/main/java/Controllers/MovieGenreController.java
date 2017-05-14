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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class MovieGenreController {

    @Autowired
    GenreService genreService;
    @Autowired
    MovieGenreService movieGenreService;
    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/moviegenres")
    protected ModelAndView getMovieGenrePage(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        ModelAndView modelandview = new ModelAndView("moviegenres");
        return modelandview;
    }

    @RequestMapping(value = "/moviegenres/{genre}")
    protected ModelAndView getMovieGenrePage(HttpServletRequest request, @PathVariable(value = "genre") String genre) {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        Genre g = genreService.getGenreByName(genre);
        List<MovieGenre> moviegenres = movieGenreService.getMovieGenresByGenre(g);
        List<Movie> movies = new ArrayList<>();

        for (MovieGenre moviegenre : moviegenres) {
            Movie movie = moviegenre.getMovie();
            Timestamp timestamp = movie.getReleaseDate();
            GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
            cal.setTime(timestamp);
            if (cal.get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR) -2  ){
                movies.add(movie);
            }          
        }

        request.setAttribute("movies", movies);
        
        ModelAndView modelandview = new ModelAndView("genreresults");
        return modelandview;
    }

}
