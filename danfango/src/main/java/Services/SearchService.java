/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Model.ClientSearchResult;
import Model.LocationSearchResult;
import Model.Movie;
import Model.SearchResults;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
//    @Autowired
//    CrewMemberService crewService;
//    @Autowired
//    TheatreService theatreService;
    
    
    public SearchResults search(String searchString){
        SearchResults results = new SearchResults();
        searchString = "%" + searchString + "%";
        ArrayList<ClientSearchResult> movies = searchMovies(searchString);
//        ArrayList<ClientSearchResult> crew = searchCrew(searchString);
//        ArrayList<ClientSearchResult> theatres = searchTheatres(searchString);
//        ArrayList<LocationSearchResult> locations = searchLocations(searchString);
        
        results.setMovies(movies);
//        results.setCrew(crew);
//        results.setTheatre(theatres);
//        results.setLocations(locations);
        System.out.println("Returned from searchservice search");
        return results;
    }
    
    public ArrayList<ClientSearchResult> searchMovies(String searchString){
        ArrayList<ClientSearchResult> movieResults = new ArrayList();
        List<Movie> movies = movieService.getMoviesLikeTitle(searchString);
        System.out.println("THE MOVIES FROM THE DB: " + Arrays.toString(movies.toArray()));
        for(Movie m: movies){
            ClientSearchResult movieResult = new ClientSearchResult();
            movieResult.setId(m.getId());
            movieResult.setName(m.getTitle());
            movieResults.add(movieResult);
        }
        System.out.println("THE MOVIE SEARCH RESULTS WE'RE RETURNING" + Arrays.toString(movieResults.toArray()));
        return movieResults;
    }
    
    public ArrayList<ClientSearchResult> searchCrew(String searchString){
        ArrayList<ClientSearchResult> crew = new ArrayList();
        return crew;
    }
    
    public ArrayList<ClientSearchResult> searchTheatres(String searchString){
        ArrayList<ClientSearchResult> theatres = new ArrayList();
        return theatres;
    }
    
    public ArrayList<LocationSearchResult> searchLocations(String searchString){
        ArrayList<LocationSearchResult> locations = new ArrayList();
        return locations;
    }
    

}
