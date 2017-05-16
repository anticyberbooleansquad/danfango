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
import Model.OrderTicket;
import Model.Order;
import Model.Seat;
import Model.Showing;
import Model.TheatreRoom;
import Model.Ticket;
import Model.TicketTypePrice;
import Services.OrderTicketService;
import Services.OrdersService;
import Services.SeatService;
import Services.TicketService;
import Services.TicketTypePriceService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    @Autowired
    OrderTicketService orderTicketService;
    @Autowired
    TicketTypePriceService ticketTypePriceService;
    
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
        // ORDER SHOULD BE MADE HERE FOR PRICING
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        ModelAndView modelandview = new ModelAndView("paymentpage");        
        return modelandview;
    }
    
    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    protected ModelAndView processPayment(@RequestParam(value="card-holder-name") String cardHolder, @RequestParam(value="card-number") String cardNumber, 
            @RequestParam(value="expiry-month") String month, @RequestParam(value="expiry-year") String year, @RequestParam(value="cvv") String cvv,
            @RequestParam(value="email") String email, HttpServletRequest request) throws MessagingException{
                
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);  
        Timestamp today = new Timestamp(System.currentTimeMillis());
        double totalPrice=0;
        //create the orders
        Order order = new Order();
        order.setEmail(email);
        order.setOrderDate(today);
        ordersService.addOrder(order);
        
        //get tickets off of the session
        HttpSession session = request.getSession();
        Showing showing = (Showing) session.getAttribute("showing");
        LiveTickets livetickets = (LiveTickets) session.getAttribute("sessionTickets");
        List<Ticket> tickets = livetickets.getLiveTicket();
        boolean reservedSeating;
        if(tickets == null){
            // create new list of tickets because didn't have any because not reserved seating
            tickets = new ArrayList<Ticket>();
            int numseats = Integer.parseInt((String) session.getAttribute("totalNumSeats"));
            for (int i=0; i<numseats;i++){
                Ticket ticket = new Ticket();
                ticket.setShowing(showing);
                tickets.add(ticket);
            }
            reservedSeating = false;
        }
        else{
            reservedSeating = true;
        }
        // add pricing
        //start
        List<TicketTypePrice> ticketPrices = ticketTypePriceService.getTicketTypePriceByTheatre(showing.getTheatre());
        double childPrice = 0;
        double adultPrice = 0;
        double seniorPrice = 0;
        // find out the prices
        for(TicketTypePrice ttp: ticketPrices){
            if(ttp.getTicketType().equals("Child")){
                childPrice = ttp.getPrice();
            }
            else if(ttp.getTicketType().equals("Senior")){
                seniorPrice = ttp.getPrice();
            }
            else if(ttp.getTicketType().equals("Adult")){
                adultPrice = ttp.getPrice();
            }
        }
        // now we need to get respective number of tickets of each type and add price for each
        int numChildren = Integer.parseInt((String) session.getAttribute("numChildren"));
        int numSeniors = Integer.parseInt((String) session.getAttribute("numSeniors"));
        int numAdults = Integer.parseInt((String) session.getAttribute("numAdults"));
        
        for(Ticket ticket: tickets){
            if(numChildren > 0){
                ticket.setTicketType(Ticket.TicketType.Child);
                ticket.setPrice(childPrice);
                numChildren--;
            }
            else if(numSeniors > 0){
                ticket.setTicketType(Ticket.TicketType.Senior);
                ticket.setPrice(seniorPrice);
                numSeniors--;
            }
            else if(numAdults > 0){
                ticket.setTicketType(Ticket.TicketType.Adult);
                ticket.setPrice(adultPrice);
                numAdults--;
            }
        }    
        //end   
        for(Ticket ticket : tickets){
            // put tickets on database 
            ticketService.addTicket(ticket);
            totalPrice += ticket.getPrice();
            // remove the tickets from the live objects list
            if (reservedSeating){
                LiveTickets ts = ticketService.getLiveTickets();
                ts.removeTicket(ticket);
            }
            // map ticket to order
            OrderTicket ot = new OrderTicket();
            ot.setOrder(order);
            ot.setTicket(ticket);
            orderTicketService.addOrderTicket(ot);
            
        }
        // need to add total price but can only be done after we map orders and tickets together
        order.setPrice(totalPrice);
        ordersService.updateOrder(order);

        // send an email with the attached 
        sendEmail(email,order);
        
        
        ModelAndView modelandview = new ModelAndView("index");        
        return modelandview;
            
        
    }
    
    
        protected void sendEmail(String email, Order order) throws AddressException, MessagingException
    {
        Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("aspencse308@gmail.com", "aspen308team");
			}
		  });
        // Recipient's email ID needs to be mentioned.
        String to = email;
        // Sender's email ID needs to be mentioned
        String from = "aspencse308@gmail.com";
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Tickets");
            // Now set the actual message
            System.out.println("orderid: " + order.getId());
            message.setText("Your orderId is: " + order.getId() + "you paid: "+ order.getPrice() );
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }  
    }

}
