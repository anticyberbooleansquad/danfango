/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author johnlegutko
 */
public class TheatreMovies {
    
    private Theatre theatre;
    private List<MovieShowings> movieShowings;

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<MovieShowings> getMovieShowings() {
        return movieShowings;
    }

    public void setMovieShowings(List<MovieShowings> movieShowings) {
        this.movieShowings = movieShowings;
    }


    
    
}
