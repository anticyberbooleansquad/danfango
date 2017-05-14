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
import Model.FavoriteMovie;
import Model.Movie;
import Model.User;
import Services.AuthenticationService;
import Services.CrewMemberMovieService;
import Services.FavoriteMovieService;
import Services.MovieService;
import Services.ReviewService;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    CrewMemberMovieService crewMemberMovieService;
    @Autowired
    FavoriteMovieService favoriteMovieService;
    //@Autowired
    //ReviewService reviewService;
    
    @RequestMapping(value = "/movieinfopage/{movieId}")
    protected ModelAndView getMovieInfoPage(@PathVariable(value="movieId") int id, HttpServletRequest request){
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        Movie movie = movieService.getMovieById(id);
        User user = (User)session.getAttribute("user");
        
        FavoriteMovie fav2 = favoriteMovieService.getFavoriteMovieByUserAndMovie(user, movie);
        
        if(fav2 != null)
        {
            request.setAttribute("favoriteState", true);
        }
        else
        {
            request.setAttribute("favoriteState", false);
        }
        
        request.setAttribute("movie", movie);

        List<CrewMemberMovie> crewMemberMovie = crewMemberMovieService.getCrewMemberMovieByMovie(movie);
        request.setAttribute("crewMemberMovie", crewMemberMovie);

        ModelAndView modelandview = new ModelAndView("movieinfopage");
        return modelandview;
    }
    
//    @RequestMapping(value = "/movieinfopage", method = RequestMethod.POST)
//    protected ModelAndView changeFavoriteState(HttpServletRequest request , HttpServletRequest response){
//        //ServletContext sc = request.getServletContext();
//        
//        ModelAndView modelandview;
//        
//        request.setAttribute("favorite", 1);
//        modelandview = new ModelAndView("movieinfopage");
//      
//        return modelandview;
//    }
    
    @RequestMapping(value = "/changeFavorite")
    public @ResponseBody String changeFavoriteState(HttpServletRequest request){
        System.out.println("fuck");
        return "success";
    }
    
    @RequestMapping(value = "/submitReview", method = RequestMethod.POST)
    protected void submitReview( @RequestParam("reviewSubject") String subject, @RequestParam("reviewContent") String content, HttpServletRequest request)
    {
        //System.out.println("star value: " + starValue);
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
