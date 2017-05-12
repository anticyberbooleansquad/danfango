/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author johnlegutko
 */
@Entity
@Table
public class Seat implements Serializable{
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private TheatreRoom theatreRoom;
    @OneToOne
    private Showing showing;
    private String row;
    private String seatNumber;
    private boolean available;

    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public TheatreRoom getTheatreRoom() {
        return theatreRoom;
    }

    public void setTheatreRoom(TheatreRoom theatreRoom) {
        this.theatreRoom = theatreRoom;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
