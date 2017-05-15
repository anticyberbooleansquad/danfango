/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charles
 */
public class LiveTickets {
    
    private List<Orders> liveOrders;
    
    public LiveTickets(){}

    public List<Orders> getLiveOrders() {
        return this.liveOrders;
    }

    public void setLiveOrders(List<Orders> liveOrders) {
        this.liveOrders = liveOrders;
    }
    
    public void addOrder(Orders order)
    {
        this.liveOrders.add(order);
    }
    
    public void removeOrder(Orders order)
    {
        this.liveOrders.remove(order);
    }
    
    public List<Orders> getOrderByShowing(Showing showing)
    {
        ArrayList orders = new ArrayList<Orders>();
        for(Orders order : this.liveOrders)
        {
            //
        }
        
        return this.liveOrders;
    }
    
    
}
