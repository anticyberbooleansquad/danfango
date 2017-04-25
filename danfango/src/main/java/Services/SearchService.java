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
import java.io.IOException;
import java.net.MalformedURLException;
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
    LocationService locationService;
    @Autowired
    MovieService movieService;
    @Autowired
    CrewMemberService crewMemberService;
    @Autowired
    TheatreService theatreService;

    
    /**
     * The main search method. 
     * theatresByLocation are only included in the search results if the user enters a zipcode or exact (city, state) combo.
     * Otherwise theatresByLocation will be null and danfango will attempt to provide location suggestions based on the
     * entered searchString.
     * @param searchString
     * @return
     * @throws MalformedURLException
     * @throws IOException 
     */
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
            movieResult.setPoster(m.getPoster());
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
            result.setPoster(member.getPoster());
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
    
    /** 
     * The main method for theatre by location searches. 
     * First checks to see if the user entered a potential zip code and if so calls searchTheatresByZipCode().
     * If not it extracts and passes city and state from the zipcode to searchTheatresNearCityState() to see if the user
     * entered an exact [city, state] location
     * @param searchString
     * @return 
     * @throws IOException 
     */
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
            System.out.println("IN SEARCH THEATRES BY LOCATION");
            String[] names = searchString.split(",");
            if (names != null && names.length > 1) {
                String city = names[0];
                String state = names[1].trim();
                System.out.println("state is: " + state);
                if (isShortStateName(state)) {
                    System.out.println("we make it here and have abbrev.");
                    theatresByLocation = searchTheatresNearCityState(city, state);
                } else if (isLongStateName(state)) {
                    System.out.println("we make it below and have full state name");
                    state = locationService.getShortNameKey(state);
                    theatresByLocation = searchTheatresNearCityState(city, state);
                }
            }
        }
        return theatresByLocation;
    }

    /**
     * Retrieves a zipcode from the locationService based on (city, state) and then make use of the
     * searchTheatresByZipcode() method
     * @param city
     * @param state
     * @return
     * @throws IOException 
     */
    public ArrayList<ClientSearchResult> searchTheatresNearCityState(String city, String state) throws IOException {
        ArrayList<ClientSearchResult> theatreResults = null;
        int zipcode = locationService.getZipcodeByCityState(city, state);
        if (zipcode != -1) {
            theatreResults = searchTheatresByZipcode(zipcode);
        }
        return theatreResults;
    }
    
    /**
     * Retrieves a list of nearby zipcodes from the locationSerivce and will return information on any
     * theatres who have a zipcode in this list. 
     * @param zipcode
     * @return
     * @throws MalformedURLException
     * @throws IOException 
     */
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
    
    /**
     * The main method for location searches.
     * We first call searchLocationsByState() in case the user entered just an exact state
     * We then call searchLocationsByCityState() in case the user entered a city substring and a [state substring/exact state]
     * If the user didn't enter an exact state or a comma separated potential city/state, we search for both cities and states 
     * matching the entered subString.
     * @param searchString
     * @return 
     */
    public ArrayList<LocationSearchResult> searchLocations(String searchString) {
        ArrayList<LocationSearchResult> locations = null;
        System.out.println("We make it to searchLocations");
        locations = searchLocationsByState(searchString);
        if (locations == null) {
            String[] names = searchString.split(",");
            if (names != null && names.length > 1) {
                String city = names[0]; // babylon
                String state = names[1].trim(); // new yor
                locations = searchLocationsByCityState(city, state);
            }
        }
        if (locations == null) {
            locations = searchLocationsBySubstring(searchString); // linde
        }
        return locations;
    }

    
    /**
     * // search in the form: [abbrev./full state]. This method checks for an exact state match
     * @param searchString
     * @return 
     */
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

    /** 
     * search in the form: [citySubstring], [potential abbrev./full state]
     * @param city
     * @param state
     * @return 
     */
    public ArrayList<LocationSearchResult> searchLocationsByCityState(String city, String state) {
        System.out.println("WE MAKE IT TO CITY STATE:   City: "+city+" State: "+ state);
        ArrayList<LocationSearchResult> locations = null;
        if (isShortStateName(state)) {
            locations = locationService.getLocationsLikeCityByState(city, state);
        } else if (isLongStateName(state)) {
            String shortStateName = locationService.getShortNameKey(state);
            locations = locationService.getLocationsLikeCityByState(city, shortStateName);
        } // state is not exact, can do our best with a like on cityname AND state
        else {
            // right now this works only with abbreviations since theatre database only has abbreviated statename
            // we should make it so that, 'lindenurst, new yo' will work too 
            locations = locationService.getLocationsLikeCityAndLikeState(city, state);
        }
        return locations;
    }
    /**
     * search in the form: [some substring]
     * user did not enter a state as any part of the searchString
     * this means we do a very general location search, returning results where searchString 
     * is a substring of the cityname OR statename of the locations
     * @param searchString
     * @return 
     */
    public ArrayList<LocationSearchResult> searchLocationsBySubstring(String searchString) {
        ArrayList<LocationSearchResult> locations = null;
        locations = locationService.getLocationsLikeCityOrLikeState(searchString);
        return locations;
    }

    /**
     * Checks if the entered string is a full state name. Ex. new york
     * @param stateName
     * @return 
     */
    public boolean isLongStateName(String stateName) {
        String longStateName = stateName;
        String shortStateName = locationService.getShortNameKey(longStateName);
        if (shortStateName != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the entered string is an abbreviated state name. Ex. ny
     * @param stateName
     * @return 
     */
    public boolean isShortStateName(String stateName) {
        String shortStateName = stateName;
        String longStateName = locationService.getFullNameValue(shortStateName);
        if (longStateName != null) {
            return true;
        } else {
            return false;
        }
    }


}
