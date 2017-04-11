/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Ticket.TicketType;
import java.sql.Time;
import java.util.HashMap;
import javax.persistence.*;

/**
 *
 * @author johnlegutko
 */
//@Entity
public class Showing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Time time;
    private Movie movie;
    private TheatreRoom theatreRoom;
    private HashMap<TicketType, Double> priceInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public TheatreRoom getTheatreRoom() {
        return theatreRoom;
    }

    public void setTheatreRoom(TheatreRoom theatreRoom) {
        this.theatreRoom = theatreRoom;
    }

    public HashMap<TicketType, Double> getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(HashMap<TicketType, Double> priceInfo) {
        this.priceInfo = priceInfo;
    }

}
