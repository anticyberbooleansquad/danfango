/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charles
 */
public class LiveTickets implements Serializable{
    
    private List<Ticket> liveTicket;
    
    public LiveTickets(){
        this.liveTicket = new ArrayList<>();

    }

    public List<Ticket> getLiveTicket() {
        return this.liveTicket;
    }

    public void setLiveTicket(List<Ticket> liveTicket) {
        this.liveTicket = liveTicket;
    }
    
    public void addTicket(Ticket ticket)
    {
        this.liveTicket.add(ticket);
    }
    
    public void removeTicket(Ticket ticket)
    {
        this.liveTicket.remove(ticket);
    }
    
    public List<Ticket> getTicketsByShowing(Showing showing)
    {
        ArrayList tickets = new ArrayList<Ticket>();
        for(Ticket ticket : this.liveTicket)
        {
            if (ticket.getShowing().equals(showing)){
                tickets.add(ticket);
            }
        }
        
        return this.liveTicket;
    }
    
    
}
