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
import Model.Theatre;
import Model.TheatreShowings;
import Services.MovieService;
import Services.ShowingService;
import Services.TheatreService;
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
public class TicketSelectController {

    @Autowired
    MovieService movieService;
    @Autowired
    ShowingService showingService;
    @Autowired
    TheatreService theatreService;

    @RequestMapping(value = "/ticketselectpage/{movieId}")
    protected ModelAndView getTicketSelectPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        Movie movie = movieService.getMovieById(id);
        movie.setRunTime(timeConvert(movie.getRunTime()));
        request.setAttribute("movie", movie);

        List<TheatreShowings> showingsPerTheatre = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            List<Showing> showingsForTheatre = showingService.getShowingByMovieAndTheatre(movie, theatre);
            TheatreShowings theatreShowings = new TheatreShowings();
            theatreShowings.setTheatre(theatre);
            theatreShowings.setShowings(showingsForTheatre);
            showingsPerTheatre.add(theatreShowings);
        }

        request.setAttribute("showingsPerTheatre", showingsPerTheatre);

        System.out.println("SHOINGSSSSSS: " + showingsPerTheatre);

        ModelAndView modelandview = new ModelAndView("ticketselectpage");
        return modelandview;
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
