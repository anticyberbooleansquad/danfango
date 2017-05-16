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
import Model.MovieShowings;
import Model.Showing;
import Model.Theatre;
import Model.TheatreMovies;
import Model.TheatreShowings;
import Services.MovieGenreService;
import Services.MovieService;
import Services.ShowingService;
import Services.TheatreService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    MovieGenreService movieGenreService;
    @Autowired
    ShowingService showingService;
    @Autowired
    TheatreService theatreService;

    @RequestMapping(value = "/ticketselectpage/{movieId}")
    protected ModelAndView getTicketSelectPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        request.setAttribute("date", dateFormat.format(date));
        Timestamp now = new Timestamp(date.getTime());


        Movie movie = movieService.getMovieById(id);
        movie.setRunTime(timeConvert(movie.getRunTime()));
        request.setAttribute("movie", movie);
        
        List<Genre> genres = new ArrayList<>();
        List<MovieGenre> movieGenres = movieGenreService.getMovieGenresByMovie(movie);
        for (MovieGenre mg : movieGenres) {
            genres.add(mg.getGenre());
        }
        request.setAttribute("genres", genres);
        

        List<TheatreShowings> showingsPerTheatre = new ArrayList<>();
        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            List<Showing> showingsForTheatre = showingService.getShowingByMovieAndTheatreAndTime(movie, theatre, now);
            System.out.println("showingsForTHeatre: " + showingsForTheatre);
            if (showingsForTheatre != null) {
                TheatreShowings theatreShowings = new TheatreShowings();
                theatreShowings.setTheatre(theatre);
                theatreShowings.setShowings(showingsForTheatre);
                showingsPerTheatre.add(theatreShowings);
            }
        }

        request.setAttribute("showingsPerTheatre", showingsPerTheatre);

        

        ModelAndView modelandview = new ModelAndView("ticketselectpage");
        return modelandview;
    }

    @RequestMapping(value = "/ticketselectpage/{movieId}/date")
    protected ModelAndView getTicketSelectPage(@PathVariable(value = "movieId") int id, @RequestParam("showingDate") String date, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        System.out.println("DATTEEEEE: " + date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(date);
            String newDateString = df.format(startDate);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp now = new Timestamp(startDate.getTime());

        Movie movie = movieService.getMovieById(id);
        movie.setRunTime(timeConvert(movie.getRunTime()));
        request.setAttribute("movie", movie);
        
        List<Genre> genres = new ArrayList<>();
        List<MovieGenre> movieGenres = movieGenreService.getMovieGenresByMovie(movie);
        for (MovieGenre mg : movieGenres) {
            genres.add(mg.getGenre());
        }
        request.setAttribute("genres", genres);
        

        List<TheatreShowings> showingsPerTheatre = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            List<Showing> showingsForTheatre = showingService.getShowingByMovieAndTheatreAndTime(movie, theatre, now);
            System.out.println("showingsForTHeatre: " + showingsForTheatre);
            if (showingsForTheatre != null) {
                TheatreShowings theatreShowings = new TheatreShowings();
                theatreShowings.setTheatre(theatre);
                theatreShowings.setShowings(showingsForTheatre);
                showingsPerTheatre.add(theatreShowings);
            }
        }

        request.setAttribute("showingsPerTheatre", showingsPerTheatre);
        request.setAttribute("date", date);

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

    public String timeConvert(String timeString) {
        int time = Integer.parseInt(timeString);
        if (time == 0) {
            return "N/A";
        } else {
            return time / 60 % 24 + " hr " + time % 60 + " min";
        }
    }
    


}
