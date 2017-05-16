/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author johnlegutko
 */
import Model.LiveTickets;
import Model.Orders;
import Model.Seat;
import Model.Showing;
import Model.TheatreRoom;
import Model.Ticket;
import Services.OrdersService;
import Services.SeatService;
import Services.TicketService;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController{
    @Autowired
    TicketService ticketService;
    @Autowired
    SeatService seatService;
    @Autowired
    OrdersService ordersService;
    
    @RequestMapping(value = "/lockSeats", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    protected ModelAndView lockSeats(@RequestParam(value="seatNumbers[]") String[] seatNumbers, HttpServletRequest request){
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        
        HttpSession session = request.getSession();
        Showing showing = (Showing) session.getAttribute("showing");
        TheatreRoom room = showing.getTheatreRoom();
        LiveTickets liveTickets = ticketService.getLiveTickets();
        LiveTickets sessionTickets = new LiveTickets();
        if(seatNumbers != null){
            for(String seatNumber: seatNumbers){
                // get seat row
                String seatRow = seatNumber.substring(0, 1);
                String seatNum = seatNumber.substring(1, seatNumber.length());
              
                // grab this seat from DB by theatreroom id, row, and column 
                Seat seat = seatService.getSeat(seatRow, seatNum, room);
                Ticket ticket = new Ticket();
                ticket.setSeat(seat);
                ticket.setShowing(showing);         
                // add this ticket to LiveTickets .addTicket
                sessionTickets.addTicket(ticket);
                liveTickets.addTicket(ticket);   
            }
        }
        session.setAttribute("sessionTickets", sessionTickets);
        ModelAndView modelandview = new ModelAndView("paymentpage");
        return modelandview;
    }
    
    @RequestMapping(value = "/unlockSeats", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    protected ModelAndView unlockSeats( HttpServletRequest request){
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        
        HttpSession session = request.getSession();
        Showing showing = (Showing) session.getAttribute("showing");
        TheatreRoom room = showing.getTheatreRoom();
        LiveTickets liveTickets = ticketService.getLiveTickets();
        LiveTickets sessionTickets = (LiveTickets)session.getAttribute("sessionTickets");
        for(Ticket ticket: sessionTickets.getLiveTicket()){
            liveTickets.removeTicket(ticket);
        }
        
        ModelAndView modelandview = new ModelAndView("paymentpage");
        return modelandview;
    }
 
    @RequestMapping(value = "/paymentpage")
    protected ModelAndView getPaymentPage(HttpServletRequest request){
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        ModelAndView modelandview = new ModelAndView("paymentpage");        
        return modelandview;
    }
    
    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    protected ModelAndView processPayment(@RequestParam(value="card-holder-name") String cardHolder, @RequestParam(value="card-number") String cardNumber, 
            @RequestParam(value="expiry-month") String month, @RequestParam(value="expiry-year") String year, @RequestParam(value="cvv") String cvv,
            @RequestParam(value="email") String email, HttpServletRequest request){
                
                String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        Timestamp today = new Timestamp(System.currentTimeMillis());
        double totalPrice=0;
        //create the orders
        Orders order = new Orders();
        order.setEmail(email);
        order.setOrderDate(today);
        ordersService.addOrder(order);
        
        //get tickets off of the session
        HttpSession session = request.getSession();
        LiveTickets livetickets = (LiveTickets) session.getAttribute("sessionTickets");
        List<Ticket> tickets = livetickets.getLiveTicket();
        boolean reservedSeating;
        if(tickets == null){
            // create new list of tickets because didn't have any because not reserved seating
            reservedSeating = false;
        }
        else{
            reservedSeating = true;
        }
        
        for(Ticket ticket : tickets){
            // put tickets on database 
            ticketService.addTicket(ticket);
            totalPrice += ticket.getPrice();
            // remove the tickets from the live objects list
            LiveTickets ts = ticketService.getLiveTickets();
            if (reservedSeating){
                ts.removeTicket(ticket);
            }
            // map ticket to order
            
            
        }
        // need to add total price but can only be done after we map orders and tickets together
        order.setPrice(totalPrice);
        ordersService.updateOrder(order);

        // send an email with the attached tickets
        
        
        ModelAndView modelandview = new ModelAndView("index");        
        return modelandview;
            
        
    }
}
