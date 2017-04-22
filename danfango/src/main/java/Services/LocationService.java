/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class LocationService {

    @Autowired
    TheatreService theatreService;

    String apiKey = "eu4HRGl5AWyldlyCHjdvzMAjYzzLrbNFFSnxyIC9EbuSx2vemIKWulftVOemZ22F";

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

        // if we make it down here this is not a valid (city, state)
        return -1;
    }

}
