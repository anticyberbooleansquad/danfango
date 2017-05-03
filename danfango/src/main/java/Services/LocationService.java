/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.LocationSearchResult;
import Model.Theatre;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All of LocationServices methods expect an abbreviated version of a state's name as this is what we have in the database for theatres.
 * The zipcodeapi also uses abbreviated state names.
 * A bidirectional map is used to map abbreviations with full names.
 * If you have the longer version of a state name, you can get the abbreviated version by passing it into 
 * the getShortNameKey() method. 
 * @author Konrad Juszkiewicz
 */
@Service
public class LocationService {

    @Autowired
    TheatreService theatreService;

    String apiKey = "t6iMOqBUYrkSGbuFwSegmdNy5LgjER18XjUMKwHIY2BEd5kJYzZlNNTSyacd1dmZ";

    private BiMap<String, String> states;

    public LocationService() {
        // fill/initialize BiMap
        Map<String, String> tempStates = new HashMap();
        fillStatesMap(tempStates);
        states = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempStates));
    }
    
    public String getShortNameKey(String fullNameValue){
        return states.inverse().get(fullNameValue);
    }
    
    public String getFullNameValue(String shortNameKey){
        return states.get(shortNameKey);
    }

    public ArrayList<String> getNearbyZipCodes(int zipcode) throws MalformedURLException, IOException {
        String zipcodeAPIUrl = "https://www.zipcodeapi.com/rest/" + apiKey + "/radius.json/"
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
            }
        }
        return zipcodes;
    }

    public int getZipcodeByCityState(String city, String state) throws MalformedURLException, IOException {
        city = city.replace(" ", "%20");
        String zipcodeAPIUrl = "https://www.zipcodeapi.com/rest/" + apiKey + "/city-zips.json/" + city + "/" + state;
        URL zipcodeAPI = new URL(zipcodeAPIUrl);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(zipcodeAPI.openStream()))) {
            String inputLine = in.readLine();
            JSONObject jsonObj = null;
            if (inputLine != null) {
                jsonObj = new JSONObject(inputLine);
                JSONArray jsonZipCodes = jsonObj.getJSONArray("zip_codes");
                if (jsonZipCodes.length() > 0) {
                    int zipcode = Integer.valueOf(jsonZipCodes.getString(0));
                    return zipcode;
                }
            }
        }
        // if we make it down here this is not an exact, valid (city, state) combo
        return -1;
    }

    public ArrayList<LocationSearchResult> getLocationsByState(String shortStateName) {
        ArrayList<LocationSearchResult> locations = null;
        List<Theatre> theatres = theatreService.getTheatresByState(shortStateName);
        if (theatres.size() > 0) {
            locations = new ArrayList();
            for (Theatre theatre : theatres) {
                String state = theatre.getState();
                String city = theatre.getCity();
                String zip = theatre.getZip();
                LocationSearchResult location = new LocationSearchResult();
                location.setState(state);
                location.setCity(city);
                location.setZipcode(Integer.valueOf(zip));
                locations.add(location);
            }
        }
        return locations;
    }

    public ArrayList<LocationSearchResult> getLocationsLikeCityByState(String city, String state) {
        city = "%" + city + "%";
        ArrayList<LocationSearchResult> locations = null;
        List<Theatre> theatres = theatreService.getTheatresLikeCityByState(city, state);
        if (theatres.size() > 0) {
            locations = new ArrayList();
            for (Theatre theatre : theatres) {
                String stateName = theatre.getState();
                String cityName = theatre.getCity();
                String zip = theatre.getZip();
                LocationSearchResult location = new LocationSearchResult();
                location.setState(stateName);
                location.setCity(cityName);
                location.setZipcode(Integer.valueOf(zip));
                locations.add(location);
            }
        }
        return locations;
    }

    public ArrayList<LocationSearchResult> getLocationsLikeCityAndLikeState(String city, String state) {
        city = "%" + city + "%"; 
        state = "%" + state + "%";
        ArrayList<LocationSearchResult> locations = null;
        List<Theatre> theatres = theatreService.getTheatresLikeCityAndLikeState(city, state);
        if (theatres.size() > 0) {
            locations = new ArrayList();
            for (Theatre theatre : theatres) {
                String stateName = theatre.getState();
                String cityName = theatre.getCity();
                String zip = theatre.getZip();
                LocationSearchResult location = new LocationSearchResult();
                location.setState(stateName);
                location.setCity(cityName);
                location.setZipcode(Integer.valueOf(zip));
                locations.add(location);
            }
        }
        return locations;
    }

    public ArrayList<LocationSearchResult> getLocationsLikeCityOrLikeState(String substring) {
        substring = "%" + substring + "%";
        ArrayList<LocationSearchResult> locations = null;
        List<Theatre> theatres = theatreService.getTheatresLikeCityOrLikeState(substring);
        System.out.println("Num theatres returned: " + theatres.size());
        if (theatres.size() > 0) {
            locations = new ArrayList();
            for (Theatre theatre : theatres) {
                String stateName = theatre.getState();
                String cityName = theatre.getCity();
                String zip = theatre.getZip();
                LocationSearchResult location = new LocationSearchResult();
                location.setState(stateName);
                location.setCity(cityName);
                location.setZipcode(Integer.valueOf(zip));
                locations.add(location);
            }
        }
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
