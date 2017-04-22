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
    LocationService locationService;
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
        ArrayList<ClientSearchResult> theatresByLocation = null;
        
        boolean zipcodeEntered = false;
        // try to match on exact zipcode
        searchString = searchString.trim();
        if (StringUtils.isNumeric(searchString) && searchString.length() == 5) {
            zipcodeEntered = true;
            int zipcode = Integer.valueOf(searchString);
            theatresByLocation = searchTheatresByZipcode(zipcode);
        } 
        // exact (city, state) combo
        else {
            String[] names = searchString.split(",");
            if (names != null) {
                String city = names[0];
                String state = names[1];
                if(isShortStateName(state)){
                    theatresByLocation = searchTheatresByCityState(city, state);
                }
                else if(isLongStateName(state)){
                    state = states.inverse().get(state);
                    theatresByLocation = searchTheatresByCityState(city, state);
                }
            }
        }
        // if we have no theatres by location we have to return suggested locations
        if (theatresByLocation == null) {
            if(!zipcodeEntered){
                // ArrayList<LocationSearchResult> locations = searchLocations(searchString);
            }  
        } 
        else {
            results.setTheatresByLocation(theatresByLocation);
        }
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

    public ArrayList<ClientSearchResult> searchTheatresByCityState(String city, String state) throws IOException {
        ArrayList<ClientSearchResult> theatreResults = null;
        int zipcode = locationService.getZipcodeByCityState(city, state);
        if (zipcode != -1) {
            theatreResults = searchTheatresByZipcode(zipcode);
        }
        return theatreResults;
    }

    public ArrayList<ClientSearchResult> searchTheatresByZipcode(int zipcode) throws MalformedURLException, IOException {
        ArrayList<ClientSearchResult> theatreResults = new ArrayList();
        ArrayList<String> zipcodes = locationService.getNearbyZipCodes(zipcode);
        List<Theatre> theatres = theatreService.getTheatresInZipList(zipcodes);
        for (Theatre theatre : theatres) {
            ClientSearchResult theatreResult = new ClientSearchResult();
            theatreResult.setId(theatre.getId());
            theatreResult.setName(theatre.getName());
            theatreResults.add(theatreResult);
        }
        return theatreResults;
    }

    public ArrayList<LocationSearchResult> searchLocations(String searchString) {
        ArrayList<LocationSearchResult> locations = new ArrayList();
        searchString = searchString.toLowerCase();
        // |||||||||||||| STATE NAME ONLY |||||||||||||||||
        // someone can potentially pass in a full state name, if so show all city combos with that state
        if (isLongStateName(searchString)) {

        }
        // someone can potentially pass in an abbrev. state name, if so show all city combos with that state
        if (isShortStateName(searchString)) {

        }
        // |||||||||||||| CITY, STATE  |||||||||||||||||
        // someone can potentially search in the form: [citySubstring], [abbrev./full state]
        String[] names = searchString.split(",");
        if (names != null) {
            String cityName = names[0];
            String stateName = names[1];
            if (isShortStateName(stateName)) {
                // call location service method looking for locations containing cityName in this state
            } else if (isLongStateName(stateName)) {
                String shortName = states.inverse().get(stateName);
                //  locations containg cityName in this state
                // call location service
            } // state is not exact, can do our best with a like on cityname AND state
            else {
                // call location service
            }
        }
        // if we get down here than user did not enter a state as any part of the searchString 
        // this means we do a very general location search, returning results where searchString is a substring of the cityname OR statename of the locations
        // call location service

        return locations;
    }

    public boolean isLongStateName(String stateName) {
        String longStateName = stateName;
        String shortStateName = states.inverse().get(longStateName);
        if (shortStateName != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isShortStateName(String stateName) {
        String shortStateName = stateName;
        String longStateName = states.get(shortStateName);
        if (longStateName != null) {
            return true;
        } else {
            return false;
        }
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
