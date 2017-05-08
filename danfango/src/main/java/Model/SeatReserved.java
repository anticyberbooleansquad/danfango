/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.sql.Timestamp;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

/**
 *
 * @author johnlegutko
 */
@Entity
@Table
public class SeatReserved implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Showing showing;
    @OneToOne
    private Seat seat;
    private Timestamp timeReserved;
    
    public SeatReserved(){}

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Timestamp getTimeReserved() {
        return timeReserved;
    }

    public void setTimeReserved(Timestamp timeReserved) {
        this.timeReserved = timeReserved;
    }
    
        
}
