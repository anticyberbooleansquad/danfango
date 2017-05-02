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
import Model.CrewMember;
import Model.CrewMemberMovie;
import Model.Movie;
import Services.CrewMemberMovieService;
import Services.CrewMemberService;
import Services.MovieService;
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
public class CrewController{
    @Autowired
    CrewMemberService crewMemberService;
    @Autowired
    CrewMemberMovieService crewMemberMovieService;
    
    @RequestMapping(value = "/actorinfopage/{crewId}")
    protected ModelAndView getCrewPage(@PathVariable(value="crewId") int id, HttpServletRequest request){
        
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        
        CrewMember crewMember = crewMemberService.getCrewMemberById(id);
        request.setAttribute("crewMember", crewMember);
        
        List<CrewMemberMovie> crewMemberMovie = crewMemberMovieService.getCrewMemberMovieByCrewMember(crewMember);
        request.setAttribute("crewMemberMovie",crewMemberMovie);
    
        ModelAndView modelandview = new ModelAndView("actorinfopage");        
        return modelandview;
    }
    
    
}

