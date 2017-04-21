/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Model.ClientSearchResult;
import Model.CrewMember;
import Model.LocationSearchResult;
import Model.Movie;
import Model.SearchResults;
import Model.Theatre;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Konrad Juszkiewicz <kjuszkiewicz95 at gmail.com>
 */
@Service
public class SearchService {
    @Autowired
    MovieService movieService;
    @Autowired
    CrewMemberService crewMemberService;
    @Autowired
    TheatreService theatreService;
    
    
    public SearchResults search(String searchString){
        SearchResults results = new SearchResults();
        String queryString = "%" + searchString + "%";
        ArrayList<ClientSearchResult> movies = searchMovies(queryString);
        ArrayList<ClientSearchResult> crew = searchCrew(queryString);
        ArrayList<ClientSearchResult> theatres = searchTheatres(queryString);
        
        searchString = searchString.trim();
        if(StringUtils.isNumeric(searchString) && searchString.length() == 5){
            int zipcode = Integer.valueOf(searchString);
        }
        
//        ArrayList<LocationSearchResult> locations = searchLocations(searchString);
        
        results.setMovies(movies);
        results.setCrew(crew);
        results.setTheatres(theatres);
//        results.setLocations(locations);
        System.out.println("Returned from searchservice search");
        return results;
    }
    
    public ArrayList<ClientSearchResult> searchMovies(String searchString){
        ArrayList<ClientSearchResult> movieResults = new ArrayList();
        List<Movie> movies = movieService.getMoviesLikeTitle(searchString);
        for(Movie m: movies){
            ClientSearchResult movieResult = new ClientSearchResult();
            movieResult.setId(m.getId());
            movieResult.setName(m.getTitle());
            movieResults.add(movieResult);
        }        
        return movieResults;
    }
    
    public ArrayList<ClientSearchResult> searchCrew(String searchString){        
        ArrayList<ClientSearchResult> crewResults = new ArrayList();
        List<CrewMember> crew = crewMemberService.getCrewMembersLikeName(searchString);        
        for(CrewMember member: crew){
            ClientSearchResult result = new ClientSearchResult();
            result.setId(member.getId());
            result.setName(member.getFullName());
            crewResults.add(result);
        }        
        return crewResults;
    }
    
    public ArrayList<ClientSearchResult> searchTheatres(String searchString){
        ArrayList<ClientSearchResult> theatreResults = new ArrayList();
        List<Theatre> theatres = theatreService.getTheatresLikeName(searchString);
        for(Theatre theatre: theatres){
            ClientSearchResult result = new ClientSearchResult();
            result.setId(theatre.getId());
            result.setName(theatre.getName());
            theatreResults.add(result);
        }
        return theatreResults;
    }
    
    public ArrayList<LocationSearchResult> searchLocations(String searchString){
        ArrayList<LocationSearchResult> locations = new ArrayList();
        return locations;
    }
    

}
