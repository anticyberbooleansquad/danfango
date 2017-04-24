/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.TheatreDAO;
import Model.Theatre;

@Service
public class TheatreService {

	private TheatreDAO theatreDAO;

	public void setTheatreDAO(TheatreDAO theatreDAO) {
		this.theatreDAO = theatreDAO;
	}

	
	@Transactional
	public void addTheatre(Theatre u) {
		this.theatreDAO.addTheatre(u);
	}

	
	@Transactional
	public void updateTheatre(Theatre u) {
		this.theatreDAO.updateTheatre(u);
	}

	
	@Transactional
	public List<Theatre> listTheatres() {
		return this.theatreDAO.listTheatres();
	}

	
	@Transactional
	public Theatre getTheatreById(int id) {
		return this.theatreDAO.getTheatreById(id);
	}
	
	@Transactional
	public void removeTheatre(int id) {
		this.theatreDAO.removeTheatre(id);
	}
        
        @Transactional
	public Theatre getTheatreByAgencyTheatreId(int id) {
		return this.theatreDAO.getTheatreByAgencyTheatreId(id);
	}
	
}
