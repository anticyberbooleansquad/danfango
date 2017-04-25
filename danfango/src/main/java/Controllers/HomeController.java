package Controllers;

/**
 *
 * @author joeg332
 */
import Model.Movie;
import Services.MovieService;
import java.util.ArrayList;
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
public class HomeController{
    
    @Autowired
    MovieService movieService;
    
    @RequestMapping(value = "/index")
    protected ModelAndView getHomePage(HttpServletRequest request){
        
        List<Movie> movies = movieService.getMoviesTopRated();
            
        request.setAttribute("movies", movies);
      
        ModelAndView modelandview = new ModelAndView("index");        
        return modelandview;
    }
    
    
}