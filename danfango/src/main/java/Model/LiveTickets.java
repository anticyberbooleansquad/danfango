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
public class LiveOrders {
    
    private List<Order> liveOrders;
    
    public LiveOrders(){}

    public List<Order> getLiveOrders() {
        return this.liveOrders;
    }

    public void setLiveOrders(List<Order> liveOrders) {
        this.liveOrders = liveOrders;
    }
    
    public void addOrder(Order order)
    {
        this.liveOrders.add(order);
    }
    
    public void removeOrder(Order order)
    {
        this.liveOrders.remove(order);
    }
    
    public List<Order> getOrderByShowing(Showing showing)
    {
        ArrayList orders = new ArrayList<Order>();
        for(Order order : this.liveOrders)
        {
            //
        }
        
        return this.liveOrders;
    }
    
    
}
