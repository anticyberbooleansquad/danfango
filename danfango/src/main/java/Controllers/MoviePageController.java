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
import Model.MovieGenre;
import Services.GenreService;
import Services.MovieGenreService;
import java.util.ArrayList;
import Model.FavoriteMovie;
import Model.Movie;
import Model.Review;
import Model.User;
import Services.CrewMemberMovieService;
import Services.FavoriteMovieService;
import java.sql.Timestamp;
import Services.MovieService;
import Services.ReviewService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    FavoriteMovieService favoriteMovieService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/movieinfopage/{movieId}")
    protected ModelAndView getMovieInfoPage(@PathVariable(value = "movieId") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        Timestamp today = new Timestamp(System.currentTimeMillis());
        request.setAttribute("date", today);

        Movie movie = movieService.getMovieById(id);
        movie.setRunTime(timeConvert(movie.getRunTime()));

        User user = (User) session.getAttribute("user");
        FavoriteMovie fav2 = favoriteMovieService.getFavoriteMovieByUserAndMovie(user, movie);

        if (fav2 != null) {
            request.setAttribute("favoriteState", true);
        } else {
            request.setAttribute("favoriteState", false);
        }

        request.setAttribute("movie", movie);

        //Reviews
        List reviews = reviewService.getReviewsByMovie(movie);
        List<Genre> genres = new ArrayList<>();
        
        List<MovieGenre> movieGenres = movieGenreService.getMovieGenresByMovie(movie);
        for (MovieGenre mg : movieGenres) {
            genres.add(mg.getGenre());
        }

        System.out.println("GENRES LIST: " + genres);
        request.setAttribute("genres", genres);

        List<CrewMemberMovie> crewMemberMovie = crewMemberMovieService.getCrewMemberMovieByMovie(movie);
        request.setAttribute("crewMemberMovie", crewMemberMovie);
        request.setAttribute("reviews", reviews);

        ModelAndView modelandview = new ModelAndView("movieinfopage");
        return modelandview;
    }

    @RequestMapping(value = "/addFavorite/{movieId}")
    public ModelAndView addFavorite(@PathVariable(value = "movieId") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Movie movie = movieService.getMovieById(id);
        FavoriteMovie favorite = new FavoriteMovie();
        favorite.setMovie(movie);
        favorite.setUser(user);
        favoriteMovieService.addFavoriteMovie(favorite);
        String redirect = "redirect:/movieinfopage/" + id;
        ModelAndView modelandview = new ModelAndView(redirect);
        return modelandview;
    }

    @RequestMapping(value = "/removeFavorite/{movieId}")
    public ModelAndView removeFavorite(@PathVariable(value = "movieId") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Movie movie = movieService.getMovieById(id);
        FavoriteMovie favorite = favoriteMovieService.getFavoriteMovieByUserAndMovie(user, movie);
        favoriteMovieService.removeFavoriteMovie(favorite.getId());
        String redirect = "redirect:/movieinfopage/" + id;
        ModelAndView modelandview = new ModelAndView(redirect);
        return modelandview;
    }

    @RequestMapping(value = "/submitReview/{movieId}", method = RequestMethod.POST)
    protected ModelAndView submitReview(@PathVariable(value = "movieId") int id, @RequestParam("rating") String rating, @RequestParam("reviewSubject") String subject, @RequestParam("reviewContent") String content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        Review review = new Review();
        review.setUser(user);
        review.setMovie(movieService.getMovieById(id));
        review.setRating(rating);
        review.setTitle(subject);
        review.setContent(content);
        reviewService.addReview(review);
        String redirect = "redirect:/movieinfopage/" + id;
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
