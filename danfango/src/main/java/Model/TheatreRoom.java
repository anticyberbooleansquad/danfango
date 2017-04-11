/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.persistence.*;
/**
 *
 * @author johnlegutko
 */
//@Entity
public class TheatreRoom {
    
    public enum SeatingType {Reserved, Nonreserved}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private ArrayList<Seat> seats;
    private SeatingType seatingType;
    private int totalSeats;
    private int totalSeatsRemaining;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public SeatingType getSeatingType() {
        return seatingType;
    }

    public void setSeatingType(SeatingType seatingType) {
        this.seatingType = seatingType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeatsRemaining() {
        return totalSeatsRemaining;
    }

    public void setTotalSeatsRemaining(int totalSeatsRemaining) {
        this.totalSeatsRemaining = totalSeatsRemaining;
    }
    
    
    
    
}
