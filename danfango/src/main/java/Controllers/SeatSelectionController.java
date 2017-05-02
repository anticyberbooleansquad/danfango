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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeatSelectionController{
    
    @RequestMapping(value = "/seatselection")
    protected ModelAndView getSeatSelectionPage(HttpServletRequest request){
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
    
        ModelAndView modelandview = new ModelAndView("seatselection");        
        return modelandview;
    }
    
    
}
