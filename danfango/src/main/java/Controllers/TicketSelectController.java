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
import Model.FavoriteMovie;
import Model.FavoriteTheatre;
import Model.Movie;
import Model.MovieGenre;
import Model.MovieShowings;
import Model.Showing;
import Model.Theatre;
import Model.TheatreMovies;
import Model.TheatreShowings;
import Services.MovieGenreService;
import Model.User;
import Services.FavoriteMovieService;
import Services.FavoriteTheatreService;
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
    MovieGenreService movieGenreService;
    @Autowired
    ShowingService showingService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    FavoriteTheatreService favoriteTheatreService;
    @Autowired
    FavoriteMovieService favoriteMovieService;

    @RequestMapping(value = "/ticketselectpage/{movieId}")
    protected ModelAndView getTicketSelectPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
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
                if (user != null && favoriteTheatreService.getFavoriteTheatreByUserAndTheatre(user, theatre) != null) {
                    theatreShowings.setFavorite(true);
                }
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        request.setAttribute("date", dateFormat.format(date));
        Timestamp now = new Timestamp(date.getTime());

        List<TheatreMovies> theatreMovies = new ArrayList<>();
        List<Theatre> allTheatresList = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            allTheatresList.add(theatre);
        }

        theatreAgencyIds = theatreAgencyIds.subList(1, 5);
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            System.out.println("THEATRE NAME:" + theatre.getName());
            TheatreMovies tm = new TheatreMovies();
            tm.setTheatre(theatre);
            List<MovieShowings> allMovieShowings = new ArrayList<>();
            if (user != null && favoriteTheatreService.getFavoriteTheatreByUserAndTheatre(user, theatre) != null) {
                tm.setFavorite(true);
            }
            //List<Showing> showingsForTheatre = showingService.getShowingByTheatre(theatre);
            List<Integer> movieAgencyIds = showingService.getMovieIdsByTheatre(theatre);
            if (movieAgencyIds != null) {
                for (int mid : movieAgencyIds) {
                    Movie movie = movieService.getMovieById(mid);
                    List<Showing> movieShowings = showingService.getShowingByMovieAndTheatreAndTime(movie, theatre, now);
                    MovieShowings ms = new MovieShowings();
                    if (user != null && favoriteMovieService.getFavoriteMovieByUserAndMovie(user, movie) != null) {
                        ms.setFavorite(true);
                    }
                    movie.setRunTime(timeConvert(movie.getRunTime()));
                    ms.setMovie(movie);
                    ms.setShowings(movieShowings);
                    allMovieShowings.add(ms);
                }

                tm.setMovieShowings(allMovieShowings);
                theatreMovies.add(tm);
            }

        }

        request.setAttribute("theatreMovies", theatreMovies);
        request.setAttribute("allTheatresList", allTheatresList);

        ModelAndView modelandview = new ModelAndView("headerticketselectpage");
        return modelandview;
    }

    @RequestMapping(value = "/headerticketselectpage/dateAndtheatre")
    protected ModelAndView getHeaderTicketSelectPageDateAndTheatre(@RequestParam("showingDate") String date, @RequestParam("theatre") int theatreId, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        if (date.equals("")) {
            System.out.println("DATE BEFORE: " + date);
            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            System.out.println("DATE AFTER: " + date);

        }

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

        List<TheatreMovies> theatreMovies = new ArrayList<>();
        List<Theatre> allTheatresList = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            allTheatresList.add(theatre);
        }

        Theatre theatre = theatreService.getTheatreById(theatreId);
        TheatreMovies tm = new TheatreMovies();
        tm.setTheatre(theatre);
        List<MovieShowings> allMovieShowings = new ArrayList<>();

        List<Integer> movieAgencyIds = showingService.getMovieIdsByTheatre(theatre);
        if (movieAgencyIds != null) {
            for (int mid : movieAgencyIds) {
                Movie movie = movieService.getMovieById(mid);
                List<Showing> movieShowings = showingService.getShowingByMovieAndTheatreAndTime(movie, theatre, now);
                MovieShowings ms = new MovieShowings();
                movie.setRunTime(timeConvert(movie.getRunTime()));
                ms.setMovie(movie);
                ms.setShowings(movieShowings);
                allMovieShowings.add(ms);
            }

            tm.setMovieShowings(allMovieShowings);
            theatreMovies.add(tm);
        }

        request.setAttribute("theatreMovies", theatreMovies);
        request.setAttribute("allTheatresList", allTheatresList);
        request.setAttribute("date", date);

        ModelAndView modelandview = new ModelAndView("headerticketselectpage");
        return modelandview;
    }

    @RequestMapping(value = "/headerticketselectpage/{theatreId}")
    protected ModelAndView getHeaderTicketSelectPageTheatreId(@PathVariable(value = "theatreId") int theatreId, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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

        List<TheatreMovies> theatreMovies = new ArrayList<>();
        List<Theatre> allTheatresList = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            allTheatresList.add(theatre);
        }

        Theatre theatre = theatreService.getTheatreById(theatreId);
        TheatreMovies tm = new TheatreMovies();
        tm.setTheatre(theatre);
        List<MovieShowings> allMovieShowings = new ArrayList<>();

        List<Integer> movieAgencyIds = showingService.getMovieIdsByTheatre(theatre);
        if (movieAgencyIds != null) {
            for (int mid : movieAgencyIds) {
                Movie movie = movieService.getMovieById(mid);
                List<Showing> movieShowings = showingService.getShowingByMovieAndTheatreAndTime(movie, theatre, now);
                MovieShowings ms = new MovieShowings();
                movie.setRunTime(timeConvert(movie.getRunTime()));
                ms.setMovie(movie);
                ms.setShowings(movieShowings);
                allMovieShowings.add(ms);
            }

            tm.setMovieShowings(allMovieShowings);
            theatreMovies.add(tm);
        }

        request.setAttribute("theatreMovies", theatreMovies);
        request.setAttribute("allTheatresList", allTheatresList);
        request.setAttribute("date", date);

        ModelAndView modelandview = new ModelAndView("headerticketselectpage");
        return modelandview;
    }

    @RequestMapping(value = "/headerticketselectpage/zip/{zipcode}")
    protected ModelAndView getHeaderTicketSelectPageZipCode(@PathVariable(value = "zipcode") String zipcode, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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

        List<TheatreMovies> theatreMovies = new ArrayList<>();
        List<Theatre> allTheatresList = new ArrayList<>();

        List<Integer> theatreAgencyIds = theatreService.getTheatreIds();
        for (int tid : theatreAgencyIds) {
            Theatre theatre = theatreService.getTheatreByAgencyTheatreId(tid);
            allTheatresList.add(theatre);
        }

        List<Theatre> theatresWithZip = theatreService.getTheatresByZip(zipcode);
        for (Theatre t : theatresWithZip) {
            TheatreMovies tm = new TheatreMovies();
            tm.setTheatre(t);

            List<MovieShowings> allMovieShowings = new ArrayList<>();

            List<Integer> movieAgencyIds = showingService.getMovieIdsByTheatre(t);
            if (movieAgencyIds != null) {
                for (int mid : movieAgencyIds) {
                    Movie movie = movieService.getMovieById(mid);
                    List<Showing> movieShowings = showingService.getShowingByMovieAndTheatreAndTime(movie, t, now);
                    MovieShowings ms = new MovieShowings();
                    movie.setRunTime(timeConvert(movie.getRunTime()));
                    ms.setMovie(movie);
                    ms.setShowings(movieShowings);
                    allMovieShowings.add(ms);
                }

                tm.setMovieShowings(allMovieShowings);
                theatreMovies.add(tm);
            }
        }

        request.setAttribute("theatreMovies", theatreMovies);
        request.setAttribute("allTheatresList", allTheatresList);
        request.setAttribute("date", date);

        ModelAndView modelandview = new ModelAndView("headerticketselectpage");
        return modelandview;
    }

//    @RequestMapping(value = "/addFavoriteTheatre/{theatreId}")
//    public ModelAndView addFavorite(@PathVariable(value = "theatreId") int id, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        Theatre theatre = theatreService.getTheatreById(id);
//        FavoriteTheatre favorite = new FavoriteTheatre();
//        favorite.setTheatre(theatre);
//        favorite.setUser(user);
//        favoriteTheatreService.addFavoriteTheatre(favorite);
//        String redirect = "redirect:/ticketselectpage/" + movId;
//        ModelAndView modelandview = new ModelAndView(redirect);
//        return modelandview;
//    }
//
//    @RequestMapping(value = "/remFavoriteTheatre/{theatreId}")
//    public ModelAndView removeFavorite(@PathVariable(value = "theatreId") int id, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        Theatre theatre = theatreService.getTheatreById(id);
//        FavoriteTheatre favorite = favoriteTheatreService.getFavoriteTheatreByUserAndTheatre(user, theatre);
//        favoriteTheatreService.removeFavoriteTheatre(favorite.getId());
//        String redirect = "redirect:/ticketselectpage/" + movId;
//        ModelAndView modelandview = new ModelAndView(redirect);
//        return modelandview;
//    }
    public String timeConvert(String timeString) {
        int time = Integer.parseInt(timeString);
        if (time == 0) {
            return "N/A";
        } else {
            return time / 60 % 24 + " hr " + time % 60 + " min";
        }
    }

}
