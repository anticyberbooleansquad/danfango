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
import Model.Seat;
import Model.Showing;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeatSelectionController {

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
            // later have to make some of these seats unavailable
            request.setAttribute("seatingMatrix", seatingMatrix);
            modelandview = new ModelAndView("seatselection");
        }
        else{
            modelandview = new ModelAndView("paymentpage");
        }
        return modelandview;
    }

}
