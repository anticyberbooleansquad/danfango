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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public SearchResults search(String searchString) throws MalformedURLException, IOException {
        System.out.println("Seach string is: " + searchString);
        SearchResults results = new SearchResults();
        String queryString = "%" + searchString + "%";
        ArrayList<ClientSearchResult> movies = searchMovies(queryString);
        ArrayList<ClientSearchResult> crew = searchCrew(queryString);
        ArrayList<ClientSearchResult> theatresByName = searchTheatresByName(queryString);

        searchString = searchString.trim();
        if (StringUtils.isNumeric(searchString) && searchString.length() == 5) {
            ArrayList<ClientSearchResult> theatresByLocation = searchTheatresByLocation(searchString);
            results.setTheatresByLocation(theatresByLocation);
        }

//        ArrayList<LocationSearchResult> locations = searchLocations(searchString);
        results.setMovies(movies);
        results.setCrew(crew);
        results.setTheatresByName(theatresByName);
//        results.setLocations(locations);
        System.out.println("Returned from searchservice search");
        return results;
    }

    public ArrayList<ClientSearchResult> searchMovies(String searchString) {
        ArrayList<ClientSearchResult> movieResults = new ArrayList();
        List<Movie> movies = movieService.getMoviesLikeTitle(searchString);
        for (Movie m : movies) {
            ClientSearchResult movieResult = new ClientSearchResult();
            movieResult.setId(m.getId());
            movieResult.setName(m.getTitle());
            movieResults.add(movieResult);
        }
        return movieResults;
    }

    public ArrayList<ClientSearchResult> searchCrew(String searchString) {
        ArrayList<ClientSearchResult> crewResults = new ArrayList();
        List<CrewMember> crew = crewMemberService.getCrewMembersLikeName(searchString);
        for (CrewMember member : crew) {
            ClientSearchResult result = new ClientSearchResult();
            result.setId(member.getId());
            result.setName(member.getFullName());
            crewResults.add(result);
        }
        return crewResults;
    }

    public ArrayList<ClientSearchResult> searchTheatresByName(String searchString) {
        ArrayList<ClientSearchResult> theatreResults = new ArrayList();
        List<Theatre> theatres = theatreService.getTheatresLikeName(searchString);
        for (Theatre theatre : theatres) {
            ClientSearchResult result = new ClientSearchResult();
            result.setId(theatre.getId());
            result.setName(theatre.getName());
            theatreResults.add(result);
        }
        return theatreResults;
    }

    public ArrayList<ClientSearchResult> searchTheatresByLocation(String searchString) throws MalformedURLException, IOException {
        ArrayList<ClientSearchResult> theatreResults = new ArrayList();
        int zipcode = Integer.valueOf(searchString);
        String zipcodeAPIUrl = "https://www.zipcodeapi.com/rest/eu4HRGl5AWyldlyCHjdvzMAjYzzLrbNFFSnxyIC9EbuSx2vemIKWulftVOemZ22F/radius.json/"
                + Integer.toString(zipcode) + "/30/miles?minimal";
        ArrayList<String> zipcodes = new ArrayList();
        URL zipcodeAPI = new URL(zipcodeAPIUrl);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(zipcodeAPI.openStream()))) {
            String inputLine = in.readLine();
            JSONObject jsonObj = null;
            if (inputLine != null) {
                jsonObj = new JSONObject(inputLine);
                JSONArray jsonZipCodes = jsonObj.getJSONArray("zip_codes");
                for (int i = 0; i < jsonZipCodes.length(); i++) {
                    String zip = jsonZipCodes.getString(i);
                    zipcodes.add(zip);
                }
                System.out.println("Zip codes is: " + Arrays.toString(zipcodes.toArray()));
                List<Theatre> theatres = theatreService.getTheatresInZipList(zipcodes);
                for (Theatre theatre : theatres) {
                    ClientSearchResult theatreResult = new ClientSearchResult();
                    theatreResult.setId(theatre.getId());
                    theatreResult.setName(theatre.getName());
                    theatreResults.add(theatreResult);
                }

            } 
        }
        return theatreResults;
    }

    public ArrayList<LocationSearchResult> searchLocations(String searchString) {
        ArrayList<LocationSearchResult> locations = new ArrayList();
        return locations;
    }

}
