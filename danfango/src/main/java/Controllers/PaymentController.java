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
import Model.Seat;
import Model.Showing;
import Model.TheatreRoom;
import Model.Ticket;
import Services.SeatService;
import Services.TicketService;
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
    
    @RequestMapping(value = "/lockSeats", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    protected ModelAndView lockSeats(@RequestParam(value="seatNumbers[]") String[] seatNumbers, HttpServletRequest request){
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        
        HttpSession session = request.getSession();
        Showing showing = (Showing) session.getAttribute("showing");
        TheatreRoom room = showing.getTheatreRoom();
        LiveTickets liveTickets = ticketService.getLiveTickets();
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
                liveTickets.addTicket(ticket);   
            }
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
    
    
}
