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

    public SearchService() {
        
    }

    public SearchResults search(String searchString) throws MalformedURLException, IOException {
        System.out.println("Seach string is: " + searchString);
        SearchResults results = new SearchResults();
        String queryString = "%" + searchString + "%";
        ArrayList<ClientSearchResult> movies = searchMovies(queryString);
        ArrayList<ClientSearchResult> crew = searchCrew(queryString);
        ArrayList<ClientSearchResult> theatresByName = searchTheatresByName(queryString);
        ArrayList<ClientSearchResult> theatresByLocation = searchTheatresByLocation(searchString);

        if (theatresByLocation == null && !StringUtils.isNumeric(searchString)) {
            ArrayList<LocationSearchResult> locations = searchLocations(searchString);
            if (locations != null) {
                results.setLocations(locations);
            }
        } else {
            results.setTheatresByLocation(theatresByLocation);
        }
        results.setMovies(movies);
        results.setCrew(crew);
        results.setTheatresByName(theatresByName);
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

    public ArrayList<ClientSearchResult> searchTheatresByLocation(String searchString) throws IOException {
        ArrayList<ClientSearchResult> theatresByLocation = null;
        searchString = searchString.trim();
        // try to match on exact zipcode
        if (StringUtils.isNumeric(searchString) && searchString.length() == 5) {
            int zipcode = Integer.valueOf(searchString);
            theatresByLocation = searchTheatresByZipcode(zipcode);
        } 
        // try to match on exact (city, state) combo
        else {
            String[] names = searchString.split(",");
            if (names != null) {
                String city = names[0];
                String state = names[1];
                if (isShortStateName(state)) {
                    theatresByLocation = searchTheatresNearCityState(city, state);
                } else if (isLongStateName(state)) {
                    state = locationService.getShortNameKey(state);
                    theatresByLocation = searchTheatresNearCityState(city, state);
                }
            }
        }
        return theatresByLocation;
    }

    public ArrayList<ClientSearchResult> searchTheatresNearCityState(String city, String state) throws IOException {
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
        ArrayList<LocationSearchResult> locations = null;
        locations = searchLocationsByState(searchString);
        if (locations == null) {
            String[] names = searchString.split(",");
            if (names != null) {
                String city = names[0];
                String state = names[1];
                locations = searchLocationsByCityState(city, state);
            }
        }
        if (locations == null) {
            locations = searchLocationsBySubstring(searchString);
        }
        return locations;
    }

    // search in the form: [abbrev./full state]
    public ArrayList<LocationSearchResult> searchLocationsByState(String searchString) {
        ArrayList<LocationSearchResult> locations = null;
        searchString = searchString.toLowerCase();
        // someone can potentially pass in a full state name, if so show all city combos with that state
        if (isLongStateName(searchString)) {
            String shortStateName = locationService.getShortNameKey(searchString);
            locations = locationService.getLocationsByState(shortStateName);
        }
        // someone can potentially pass in an abbrev. state name, if so show all city combos with that state
        else if (isShortStateName(searchString)) {
            locations = locationService.getLocationsByState(searchString);
        }
        return locations;
    }

    // someone can potentially search in the form: [citySubstring], [abbrev./full state]
    public ArrayList<LocationSearchResult> searchLocationsByCityState(String city, String state) {
        ArrayList<LocationSearchResult> locations = null;
        if (isShortStateName(state)) {
            locations = locationService.getLocationsLikeCityByState(city, state);
        } else if (isLongStateName(state)) {
            String shortStateName = locationService.getShortNameKey(state);
            locations = locationService.getLocationsLikeCityByState(city, shortStateName);
        } // state is not exact, can do our best with a like on cityname AND state
        else {
            locations = locationService.getLocationsLikeCityAndLikeState(city, state);
        }
        return locations;
    }

    // user did not enter a state as any part of the searchString 
    // this means we do a very general location search, returning results where searchString is a substring of the cityname OR statename of the locations
    public ArrayList<LocationSearchResult> searchLocationsBySubstring(String searchString) {
        ArrayList<LocationSearchResult> locations = null;
        locations = locationService.getLocationsLikeCityOrLikeState(searchString);
        return locations;
    }

    public boolean isLongStateName(String stateName) {
        String longStateName = stateName;
        String shortStateName = locationService.getShortNameKey(longStateName);
        if (shortStateName != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isShortStateName(String stateName) {
        String shortStateName = stateName;
        String longStateName = locationService.getShortNameKey(shortStateName);
        if (longStateName != null) {
            return true;
        } else {
            return false;
        }
    }


}
