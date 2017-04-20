/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.ClientSearchResult;
import Model.SearchResults;
import Services.SearchService;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Controller
public class SearchController {
    
    @Autowired
    SearchService searchService;
    
    @RequestMapping(value = "/search", method=RequestMethod.POST)
    protected ModelAndView getSearchResultPage(@RequestParam("searchString") String searchString, HttpServletRequest request){
        // here is where we call the search service
        SearchResults searchResults = searchService.search(searchString);
        ArrayList<ClientSearchResult> movies = searchResults.getMovies();
        System.out.println("We make it to the search controller");
        System.out.print(Arrays.toString(movies.toArray()));
        request.setAttribute("movies", movies);
                
        ModelAndView modelAndView = new ModelAndView("searchresultspage");
        return modelAndView;
    }
    

}
