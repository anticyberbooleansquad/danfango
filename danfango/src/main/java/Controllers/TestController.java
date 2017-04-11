package Controllers;

/**
 *
 * @author joeg332
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController{
    
    @RequestMapping("/test")
    protected ModelAndView staticPage(){
    
        ModelAndView modelandview = new ModelAndView("test");
        modelandview.addObject("isloggedin","true");
        
        return modelandview;
    }
}