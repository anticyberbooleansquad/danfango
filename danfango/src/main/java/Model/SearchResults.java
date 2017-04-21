/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SearchResults {
    ArrayList<ClientSearchResult> movies;
    ArrayList<ClientSearchResult> crew;
    ArrayList<ClientSearchResult> theatres;
    ArrayList<LocationSearchResult> locations;

    public ArrayList<ClientSearchResult> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<ClientSearchResult> movies) {
        this.movies = movies;
    }

    public ArrayList<ClientSearchResult> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<ClientSearchResult> crew) {
        this.crew = crew;
    }

    public ArrayList<ClientSearchResult> getTheatres() {
        return theatres;
    }

    public void setTheatres(ArrayList<ClientSearchResult> theatre) {
        this.theatres = theatre;
    }

    public ArrayList<LocationSearchResult> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<LocationSearchResult> locations) {
        this.locations = locations;
    }
            
     
    

}
