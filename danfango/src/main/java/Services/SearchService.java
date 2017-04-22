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
        // fill/initialize BiMap
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
        // ArrayList<LocationSearchResult> locations = searchLocations(searchString);

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
        // first set search string to lowercase 
        searchString = searchString.toLowerCase();
        // someone can potentially pass in a full state name, if so show all city combos with that state
        String longStateName = searchString;
        String shortStateName = states.inverse().get(longStateName);
        // state will be non-null if the search string was a statename
        if(shortStateName != null){
            
        }
        // someone can potentially pass in an abbrev. state name, if so show all city combos with that state
        shortStateName = searchString;
        longStateName = states.get(shortStateName);
        // state will be non-null if the search string was an abbrev. state
        if(longStateName != null){
            
        }
        // someone can potentially search in the form: [cityname], [abbrev./full state]
        String[] names = searchString.split(",");
        if(names != null){
            String cityName = names[0];
            String stateName = names[1];
            // stateName may be either full name or abbrev.
            // let's see if user typed an abbrev. state name
            shortStateName = stateName;
            longStateName = states.get(shortStateName);
            if(longStateName != null){
                // stateName is in fact an abbreviated state name and we can call theatre service method looking for theatres based on (city, abbrev. state) combo
                // call theatreService
            }
            // user didn't type a shortStateName, let's see if he typed a longStateName
            else{
                longStateName = stateName;
                shortStateName = states.inverse().get(longStateName);
                if(shortStateName != null){
                    // stateName is in fact a full state name and we can call theatre service method looking for theatres based on (city, abbrev. state) combo
                    // call theatreService
                }
            }
        }
        // someone can potentially pass in a city, if so show all city, state combos
        // call theatreService method to search based on city name 
        
        return locations;
    }

    public final void fillStatesMap(Map<String, String> states) {
        states.put("al", "alabama");
        states.put("mt", "montana");
        states.put("ak", "alaska");
        states.put("ne", "nebraska");
        states.put("az", "arizona");
        states.put("nv", "nevada");
        states.put("ar", "arkansas");
        states.put("nh", "new hampshire");
        states.put("ca", "california");
        states.put("nj", "new jersey");
        states.put("co", "colorado");
        states.put("nm", "new mexico");
        states.put("ct", "connecticut");
        states.put("ny", "new york");
        states.put("de", "delaware");
        states.put("nc", "north carolina");
        states.put("fl", "florida");
        states.put("nd", "north dakota");
        states.put("ga", "georgia");
        states.put("oh", "ohio");
        states.put("hi", "hawaii");
        states.put("ok", "oklahoma");
        states.put("id", "idaho");
        states.put("or", "oregon");
        states.put("il", "illinois");
        states.put("pa", "pennsylvania");
        states.put("in", "indiana");
        states.put("ri", "rhode island");
        states.put("ia", "iowa");
        states.put("sc", "south carolina");
        states.put("ks", "kansas");
        states.put("sd", "south dakota");
        states.put("ky", "kentucky");
        states.put("tn", "tennessee");
        states.put("la", "louisiana");
        states.put("tx", "texas");
        states.put("me", "maine");
        states.put("ut", "utah");
        states.put("md", "maryland");
        states.put("vt", "vermont");
        states.put("ma", "massachusetts");
        states.put("va", "virginia");
        states.put("mi", "michigan");
        states.put("wa", "washington");
        states.put("mn", "minnesota");
        states.put("wv", "west virginia");
        states.put("ms", "mississippi");
        states.put("wi", "wisconsin");
        states.put("mo", "missouri");
        states.put("wy", "wyoming");

    }

}
