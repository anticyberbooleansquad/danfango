/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.SeatDAO;
import Model.Seat;
import Model.TheatreRoom;

@Service
public class SeatService {

	private SeatDAO seatDAO;

	public void setSeatDAO(SeatDAO seatDAO) {
		this.seatDAO = seatDAO;
	}

	
	@Transactional
	public void addSeat(Seat u) {
		this.seatDAO.addSeat(u);
	}

	
	@Transactional
	public void updateSeat(Seat u) {
		this.seatDAO.updateSeat(u);
	}

	
	@Transactional
	public List<Seat> listSeats() {
		return this.seatDAO.listSeats();
	}

	
	@Transactional
	public Seat getSeatById(int id) {
		return this.seatDAO.getSeatById(id);
	}
        
        @Transactional
	public List<Seat> getSeatsByTheatreRoom(TheatreRoom theatreRoom) {
		return this.seatDAO.getSeatsByTheatreRoom(theatreRoom);
	}
	
	@Transactional
	public void removeSeat(int id) {
		this.seatDAO.removeSeat(id);
	}
        @Transactional
        public Seat getSeat(String row, String seatNum, TheatreRoom theatreRoom){
                return this.seatDAO.getSeat(row, seatNum, theatreRoom);
        }
        	
}
