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
import Model.Ticket;
import Services.OrdersService;
import Services.SeatService;
import Services.TicketService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeatSelectionController {

    @Autowired
    SeatService seatService;
    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/seatselection")
    protected ModelAndView getSeatSelectionPage(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        String seatingLayout = null;
        HttpSession session = request.getSession();
        Showing showing = (Showing) session.getAttribute("showing");

        ModelAndView modelandview;
        seatingLayout = showing.getTheatreRoom().getLayout();

        if (seatingLayout != null) {
            seatingLayout = seatingLayout.replaceAll(" ", "");
            String[] rowsArray = seatingLayout.split("R");
            int numColumns = rowsArray[0].replaceAll(",", "").length();
            int numRows = rowsArray.length;
            Seat[][] seatingMatrix = new Seat[numRows][numColumns];
            int seatValue = 0;

            char seatRow = 'A';
            int seatNum = 1;
            System.out.println("NumRows: " + numRows);
            System.out.println("NumColumns: " + numColumns);
            for (int rowIndex = 0; rowIndex < rowsArray.length; rowIndex++) {
                String rowLayout = rowsArray[rowIndex];
                String[] seatValuesArray = rowLayout.split(",");
                for (int seatIndex = 0; seatIndex < seatValuesArray.length; seatIndex++) {
                    if (!seatValuesArray[seatIndex].equals("")) {
                        seatValue = Integer.parseInt(seatValuesArray[seatIndex]);
                    }
                    Seat seat = null;
                    if (seatValue == 1) {
                        seat = new Seat();
                        seat.setRow(String.valueOf(seatRow));
                        seat.setSeatNumber(Integer.toString(seatNum));
                        seat.setAvailable(true);
                        seatNum++;
                    }
                    seatingMatrix[rowIndex][seatIndex] = seat;
                }
                seatRow = (char) (seatRow + 1);
                seatNum = 1;
            }
            // make purchased seats unavailable
            List<Seat> purchasedSeats = seatService.getPurchasedSeatsByShowing(showing);
            for (Seat seat : purchasedSeats) {
                char purchasedSeatRow = seat.getRow().charAt(0);
                int rowIndex = ((int) purchasedSeatRow) - ((int) 'A');
                // now that we have the right row seat for this seat search all of the columns for it
                for (int i = 0; i < numColumns; i++) {
                    Seat seatInMatrix = seatingMatrix[rowIndex][i];
                    if (seatInMatrix != null) {
                        if (seatInMatrix.getId().equals(seat.getId())) {
                            seatInMatrix.setAvailable(false);
                        }
                    }
                }
            }
            // make all locked live seats unavailable
            LiveTickets ts = ticketService.getLiveTickets();
            List<Ticket> tickets = ts.getOrderByShowing(showing);
            for (Ticket ticket : tickets) {
                Seat seat = ticket.getSeat();
                char purchasedSeatRow = seat.getRow().charAt(0);
                int rowIndex = ((int) purchasedSeatRow) - ((int) 'A');
                for (int i = 0; i < numColumns; i++) {
                    Seat seatInMatrix = seatingMatrix[rowIndex][i];
                    if (seatInMatrix != null) {
                        if (seatInMatrix.getId().equals(seat.getId())) {
                            seatInMatrix.setAvailable(false);
                        }
                    }
                }
            }

            request.setAttribute("seatingMatrix", seatingMatrix);
            modelandview = new ModelAndView("seatselection");
        } else {
            modelandview = new ModelAndView("paymentpage");
        }
        return modelandview;
    }

}
