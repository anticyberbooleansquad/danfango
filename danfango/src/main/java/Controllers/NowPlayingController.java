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
import Model.Showing;
import Services.MovieService;
import Services.ShowingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
public class NowPlayingController {

    @Autowired
    MovieService movieService;
    @Autowired
    ShowingService showingService;

    @RequestMapping(value = "/nowplaying")
    protected ModelAndView getNowPlayingPage(HttpServletRequest request) {
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        HashSet<Movie> nowPlayingHS = new HashSet<>();
        
        List<Showing> showings = showingService.getShowingByTimestamp(); 
        List<Movie> openingThisWeek = movieService.getMoviesOpeningThisWeek();
        List<Movie> nowPlayingTemp = movieService.getMoviesNowPlaying();
        
        for(Movie m: nowPlayingTemp){
            for(Showing s: showings){
                if(s.getMovie().getTitle().equals(m.getTitle())){
                    nowPlayingHS.add(m);
                }
            }
        }
        
        List<Movie> nowPlaying = new ArrayList<>(nowPlayingHS);

        request.setAttribute("openingThisWeek", openingThisWeek);
        request.setAttribute("nowPlaying", nowPlaying);
        
        ModelAndView modelandview = new ModelAndView("nowplaying");
        return modelandview;
    }

}
