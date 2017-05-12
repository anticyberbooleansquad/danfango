package Controllers;

/**
 *
 * @author joeg332
 */
import Model.User;
import Configuration.MyServletContextListener;
import Services.AgencyService;
import Services.AuthenticationService;
import Services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired

    UserService userService;
    @Autowired
    AgencyService agencyService;

    @RequestMapping(value = "/signinpage")
    protected ModelAndView getSignInPage(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        ModelAndView modelandview = new ModelAndView("signinpage");
        return modelandview;
    }

    @RequestMapping(value = "/submitCredentials", method = RequestMethod.POST)
    protected ModelAndView submitCredentials(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView modelandview;

        boolean authenticated = authenticationService.authenticate(email, password);
        if (authenticated) {
            User user = userService.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            modelandview = new ModelAndView("index");
        } else {
            modelandview = new ModelAndView("signinpage");
            modelandview.addObject("signinError", "Incorrect credentials entered. Please try again.");
        }
        return modelandview;
    }

    //this is temporary we will make this function fully and not hard code it 
    @RequestMapping(value = "/submitAgencyCredentials")
    protected ModelAndView submitAgencyCredentials() throws Exception {
        ModelAndView modelandview;
//        agencyService.parseFile("movie");
//        agencyService.parseFile("trailers");
//        agencyService.parseFile("actor");
        //agencyService.parseFile("theatre");
        modelandview = new ModelAndView("index");
        return modelandview;

    }

    @RequestMapping(value = "/updateMovies")
    protected ModelAndView updateMovies() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("movie");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateTheatres")
    protected ModelAndView updateTheatres() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("theatre");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateShowings")
    protected ModelAndView updateShowings() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("showing");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateTheatreRooms")
    protected ModelAndView updateTheatreRooms() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("theatreRoom");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/logout")
    protected ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        ModelAndView modelandview = new ModelAndView("index");
        return modelandview;
    }
}
