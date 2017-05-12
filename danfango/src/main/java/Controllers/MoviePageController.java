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
import Model.Genre;
import Model.Movie;
import Model.MovieGenre;
import Services.CrewMemberMovieService;
import Services.GenreService;
import Services.MovieGenreService;
import Services.MovieService;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoviePageController {

    @Autowired
    MovieService movieService;
    @Autowired
    MovieGenreService movieGenreService;
    @Autowired
    GenreService genreService;
    @Autowired
    CrewMemberMovieService crewMemberMovieService;

    @RequestMapping(value = "/movieinfopage/{movieId}")
    protected ModelAndView getMovieInfoPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        Movie movie = movieService.getMovieById(id);
        movie.setRunTime(timeConvert(movie.getRunTime()));
        request.setAttribute("movie", movie);
        
        List<Genre> genres = new ArrayList<>();
        
        List<MovieGenre> movieGenres = movieGenreService.getMovieGenresByMovie(movie);
        for(MovieGenre mg: movieGenres){
            genres.add(mg.getGenre());
        }
        
        System.out.println("GENRES LIST: "+ genres);
        
        request.setAttribute("genres", genres);

        List<CrewMemberMovie> crewMemberMovie = crewMemberMovieService.getCrewMemberMovieByMovie(movie);
        request.setAttribute("crewMemberMovie", crewMemberMovie);

        ModelAndView modelandview = new ModelAndView("movieinfopage");
        return modelandview;
    }

    @RequestMapping(value = "/movieinfopage", method = RequestMethod.POST)
    protected ModelAndView changeFavoriteState(HttpServletRequest request, HttpServletRequest response) {
        //ServletContext sc = request.getServletContext();

        ModelAndView modelandview;

        request.setAttribute("favorite", 1);
        modelandview = new ModelAndView("movieinfopage");

        return modelandview;
    }

    @RequestMapping(value = "/getFavorite", method = RequestMethod.POST)
    protected ModelAndView getFavorite(HttpServletRequest request, HttpServletRequest response) {
        //ServletContext sc = request.getServletContext();

        ModelAndView modelandview;

        //request.setAttribute("favorite", 1);
        modelandview = new ModelAndView("index");

        return modelandview;
    }

    public String getFavorite(HttpServletRequest request) {

        return "success";

    }

    public String timeConvert(String timeString) {
        int time = Integer.parseInt(timeString);
        if (time == 0) {
            return "N/A";
        } else {
            return time / 60 % 24 + " hr " + time % 60 + " min";
        }
    }
}
