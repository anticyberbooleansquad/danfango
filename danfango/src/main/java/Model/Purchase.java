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
public class Purchase {
    
    private Order order;
    private List<OrderTicket> tickets;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<OrderTicket> tickets) {
        this.tickets = tickets;
    }
    
    
    
}
