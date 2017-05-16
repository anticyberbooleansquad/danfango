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
import Model.FavoriteMovie;
import Model.FavoriteTheatre;
import Model.Movie;
import Model.MovieShowings;
import Model.Showing;
import Model.Theatre;
import Model.TheatreMovies;
import Model.TheatreShowings;
import Model.User;
import Services.FavoriteTheatreService;
import Services.MovieService;
import Services.ShowingService;
import Services.TheatreService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    FavoriteTheatreService favoriteTheatreService;

    @RequestMapping(value = "/ticketselectpage/{movieId}")
    protected ModelAndView getTicketSelectPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
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
            if(user != null && favoriteTheatreService.getFavoriteTheatreByUserAndTheatre(user, theatre) != null)
            {
                theatreShowings.setFavorite(true);
            }
            showingsPerTheatre.add(theatreShowings);
        }

        request.setAttribute("showingsPerTheatre", showingsPerTheatre);

        System.out.println("SHOINGSSSSSS: " + showingsPerTheatre);

        ModelAndView modelandview = new ModelAndView("ticketselectpage");
        return modelandview;
    }
    
    
    
    @RequestMapping(value = "/headerticketselectpage")
    protected ModelAndView getHeaderTicketSelectPage(HttpServletRequest request) {
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        List<TheatreMovies> theatreMovies = new ArrayList<>();
        
        
//        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
//        for(int tid: theatreAgencyIds){
//            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
//            System.out.println("THEATRE NAME:" + theatre.getName());
//            TheatreMovies tm = new TheatreMovies();
//            tm.setTheatre(theatre);
//            List<MovieShowings> allMovieShowings = new ArrayList<>();
//
//            //List<Showing> showingsForTheatre = showingService.getShowingByTheatre(theatre);
//            List<Integer> movieAgencyIds = showingService.getMovieIdsByTheatre(theatre);
//            for(int mid: movieAgencyIds){
//                Movie movie = movieService.getMovieById(mid);
//                List<Showing> movieShowings = showingService.getShowingByMovieAndTheatre(movie, theatre);
//                MovieShowings  ms = new MovieShowings();
//                ms.setMovie(movie);
//                ms.setShowings(movieShowings);
//                allMovieShowings.add(ms);
//            }
//            
//            tm.setMovieShowings(allMovieShowings);
//            theatreMovies.add(tm);
//                    
//        }
        
        request.setAttribute("theatreMovies", theatreMovies);

        ModelAndView modelandview = new ModelAndView("headerticketselectpage");
        return modelandview;
    }
    
    @RequestMapping(value = "/addFavoriteTheatre/{theatreId}/{movieId}")
    public ModelAndView addFavorite(@PathVariable(value="theatreId") int id, @PathVariable(value="movieId") int movId, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Theatre theatre = theatreService.getTheatreById(id);
        FavoriteTheatre favorite = new FavoriteTheatre();
        favorite.setTheatre(theatre);
        favorite.setUser(user);
        favoriteTheatreService.addFavoriteTheatre(favorite);
        String redirect = "redirect:/ticketselectpage/" + movId;
        ModelAndView modelandview = new ModelAndView(redirect);
        return modelandview;
    }
    
    @RequestMapping(value = "/remFavoriteTheatre/{theatreId}/{movieId}")
    public ModelAndView removeFavorite(@PathVariable(value="theatreId") int id, @PathVariable(value="movieId") int movId, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Theatre theatre = theatreService.getTheatreById(id);
        FavoriteTheatre favorite = favoriteTheatreService.getFavoriteTheatreByUserAndTheatre(user, theatre);
        favoriteTheatreService.removeFavoriteTheatre(favorite.getId());
        String redirect = "redirect:/ticketselectpage/" + movId;
        ModelAndView modelandview = new ModelAndView(redirect);
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
