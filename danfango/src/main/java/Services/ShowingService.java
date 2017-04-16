/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.ShowingDAO;
import Model.Showing;

@Service
public class ShowingService {

	private ShowingDAO showingDAO;

	public void setShowingDAO(ShowingDAO showingDAO) {
		this.showingDAO = showingDAO;
	}

	
	@Transactional
	public void addShowing(Showing u) {
		this.showingDAO.addShowing(u);
	}

	
	@Transactional
	public void updateShowing(Showing u) {
		this.showingDAO.updateShowing(u);
	}

	
	@Transactional
	public List<Showing> listShowings() {
		return this.showingDAO.listShowings();
	}

	
	@Transactional
	public Showing getShowingById(int id) {
		return this.showingDAO.getShowingById(id);
	}
	
	@Transactional
	public void removeShowing(int id) {
		this.showingDAO.removeShowing(id);
	}
	
}
