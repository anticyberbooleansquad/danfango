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
import Model.Showing;
import Model.Theatre;
import Model.TicketTypePrice;
import Services.ShowingService;
import Services.TicketTypePriceService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckOutController {

    @Autowired
    ShowingService showingService;
    @Autowired
    TicketTypePriceService ticketTypePriceService;

    @RequestMapping(value = "/checkoutpage/{showingId}")
    protected ModelAndView getCheckOutPage(@PathVariable(value = "showingId") int id, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        // lets start a session
        HttpSession session = request.getSession();
        // lets get the showing object
        Showing showing = showingService.getShowingById(id);
        session.setAttribute("showing", showing);
        // get showing and store on session 

        Theatre theatre = showing.getTheatre();
        TicketTypePrice adult = ticketTypePriceService.getTicketTypePriceByTheatreAndType(theatre, "Adult");
        TicketTypePrice child = ticketTypePriceService.getTicketTypePriceByTheatreAndType(theatre, "Child");
        TicketTypePrice senior = ticketTypePriceService.getTicketTypePriceByTheatreAndType(theatre, "Senior");

        request.setAttribute("adult", adult);
        request.setAttribute("child", child);
        request.setAttribute("senior", senior);

        ModelAndView modelandview = new ModelAndView("checkoutpage");
        return modelandview;
    }

}
