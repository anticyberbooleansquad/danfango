package Controllers;

/**
 *
 * @author joeg332
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
public class HomeController{
    
    @Autowired
    MovieService movieService;
    @Autowired 
    ShowingService showingService;
    
    @RequestMapping(value = "/index")
    protected ModelAndView getHomePage(HttpServletRequest request){
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        HashSet<Movie> nowPlayingHS = new HashSet<>();

        List<Showing> showings = showingService.getShowingByTimestamp();
        List<Movie> nowPlayingTemp = movieService.getMoviesNowPlaying();

        for (Movie m : nowPlayingTemp) {
            for (Showing s : showings) {
                if (s.getMovie().getTitle().equals(m.getTitle())) {
                    nowPlayingHS.add(m);
                }
            }
        }

        List<Movie> movies = new ArrayList<>(nowPlayingHS);

            
        request.setAttribute("movies", movies);
      
        ModelAndView modelandview = new ModelAndView("index");        
        return modelandview;
    }
    
    
}