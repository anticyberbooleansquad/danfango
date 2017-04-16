/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.TheatreRoomDAO;
import Model.TheatreRoom;

@Service
public class TheatreRoomService {

	private TheatreRoomDAO theatreRoomDAO;

	public void setTheatreRoomDAO(TheatreRoomDAO theatreRoomDAO) {
		this.theatreRoomDAO = theatreRoomDAO;
	}

	
	@Transactional
	public void addTheatreRoom(TheatreRoom u) {
		this.theatreRoomDAO.addTheatreRoom(u);
	}

	
	@Transactional
	public void updateTheatreRoom(TheatreRoom u) {
		this.theatreRoomDAO.updateTheatreRoom(u);
	}

	
	@Transactional
	public List<TheatreRoom> listTheatreRooms() {
		return this.theatreRoomDAO.listTheatreRooms();
	}

	
	@Transactional
	public TheatreRoom getTheatreRoomById(int id) {
		return this.theatreRoomDAO.getTheatreRoomById(id);
	}
	
	@Transactional
	public void removeTheatreRoom(int id) {
		this.theatreRoomDAO.removeTheatreRoom(id);
	}
	
}
