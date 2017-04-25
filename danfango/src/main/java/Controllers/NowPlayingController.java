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
public class NowPlayingController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/nowplaying")
    protected ModelAndView getNowPlayingPage(HttpServletRequest request) {

        List<Movie> openingThisWeek = movieService.getMoviesOpeningThisWeek();
        List<Movie> nowPlaying = movieService.getMoviesNowPlaying();

        request.setAttribute("openingThisWeek", openingThisWeek);
        request.setAttribute("nowPlaying", nowPlaying);
        


        ModelAndView modelandview = new ModelAndView("nowplaying");
        return modelandview;
    }

}
