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
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    BiMap<String, String> states;

    public SearchService() {
        // initialize BiMap
        Map<String, String> tempStates = new HashMap();
        fillStatesMap(tempStates);
        states = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempStates));
    }

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
        // someone can potentially pass in a city, if so show all city, state combos
        // someone can potentially pass in a full state name, if so show all city combos with that state
        // someone can potentially pass in an abbrev. state name, if so show all city combos with that state
        // someone can potentially pass in a city, full state, if so show all city combos with that that state 
        // someone can potentially pass in a city, abbrev. state, if so show all city combos with that state 
        return locations;
    }

    public final void fillStatesMap(Map<String, String> states) {
        states.put("AL", "Alabama");
        states.put("MT", "Montana");
        states.put("AK", "Alaska");
        states.put("NE", "Nebraska");
        states.put("AZ", "Arizona");
        states.put("NV", "Nevada");
        states.put("AR", "Arkansas");
        states.put("NH", "New Hampshire");
        states.put("CA", "California");
        states.put("NJ", "New Jersey");
        states.put("CO", "Colorado");
        states.put("NM", "New Mexico");
        states.put("CT", "Connecticut");
        states.put("NY", "New York");
        states.put("DE", "Delaware");
        states.put("NC", "North Carolina");
        states.put("FL", "Florida");
        states.put("ND", "North Dakota");
        states.put("GA", "Georgia");
        states.put("OH", "Ohio");
        states.put("HI", "Hawaii");
        states.put("OK", "Oklahoma");
        states.put("ID", "Idaho");
        states.put("OR", "Oregon");
        states.put("IL", "Illinois");
        states.put("PA", "Pennsylvania");
        states.put("IN", "Indiana");
        states.put("RI", "Rhode Island");
        states.put("IA", "Iowa");
        states.put("SC", "South Carolina");
        states.put("KS", "Kansas");
        states.put("SD", "South Dakota");
        states.put("KY", "Kentucky");
        states.put("TN", "Tennessee");
        states.put("LA", "Louisiana");
        states.put("TX", "Texas");
        states.put("ME", "Maine");
        states.put("UT", "Utah");
        states.put("MD", "Maryland");
        states.put("VT", "Vermont");
        states.put("MA", "Massachusetts");
        states.put("VA", "Virginia");
        states.put("MI", "Michigan");
        states.put("WA", "Washington");
        states.put("MN", "Minnesota");
        states.put("WV", "West Virginia");
        states.put("MS", "Mississippi");
        states.put("WI", "Wisconsin");
        states.put("MO", "Missouri");
        states.put("WY", "Wyoming");
    }

}
