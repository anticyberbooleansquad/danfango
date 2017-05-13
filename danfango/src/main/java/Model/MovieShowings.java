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
public class MovieShowings {
    
    private Movie movie;
    private List<Showing> showings;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
    
    
    
}
