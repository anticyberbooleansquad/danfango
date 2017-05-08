/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.SeatReservedDAO;
import Model.SeatReserved;

@Service
public class SeatReservedService {

	private SeatReservedDAO seatReservedDAO;

	public void setSeatReservedDAO(SeatReservedDAO seatReservedDAO) {
		this.seatReservedDAO = seatReservedDAO;
	}

	
	@Transactional
	public void addSeatReserved(SeatReserved u) {
		this.seatReservedDAO.addSeatReserved(u);
	}

	
	@Transactional
	public void updateSeatReserved(SeatReserved u) {
		this.seatReservedDAO.updateSeatReserved(u);
	}

	
	@Transactional
	public List<SeatReserved> listSeatReserveds() {
		return this.seatReservedDAO.listSeatReserveds();
	}

	
	@Transactional
	public SeatReserved getSeatReservedById(int id) {
		return this.seatReservedDAO.getSeatReservedById(id);
	}
        
        @Transactional
	public SeatReserved getSeatReservedByEmail(String email) {
		return this.seatReservedDAO.getSeatReservedByEmail(email);
	}
	
	@Transactional
	public void removeSeatReserved(int id) {
		this.seatReservedDAO.removeSeatReserved(id);
	}
        	
}
