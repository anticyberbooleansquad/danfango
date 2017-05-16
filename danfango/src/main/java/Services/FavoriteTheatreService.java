/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.FavoriteTheatreDAO;
import Model.FavoriteTheatre;
import Model.Theatre;
import Model.User;

@Service
public class FavoriteTheatreService {

	private FavoriteTheatreDAO favoriteTheatreDAO;

	public void setFavoriteTheatreDAO(FavoriteTheatreDAO favoriteTheatreDAO) {
		this.favoriteTheatreDAO = favoriteTheatreDAO;
	}

	
	@Transactional
	public void addFavoriteTheatre(FavoriteTheatre u) {
		this.favoriteTheatreDAO.addFavoriteTheatre(u);
	}

	
	@Transactional
	public void updateFavoriteTheatre(FavoriteTheatre u) {
		this.favoriteTheatreDAO.updateFavoriteTheatre(u);
	}

	
	@Transactional
	public List<FavoriteTheatre> listFavoriteTheatres() {
		return this.favoriteTheatreDAO.listFavoriteTheatres();
	}

	
	@Transactional
	public FavoriteTheatre getFavoriteTheatreById(int id) {
		return this.favoriteTheatreDAO.getFavoriteTheatreById(id);
	}
        
        @Transactional
	public FavoriteTheatre getFavoriteTheatreByUserAndTheatre(User user, Theatre theatre) {
		return this.favoriteTheatreDAO.getFavoriteTheatreByUserAndTheatre(user, theatre);
	}
	
	@Transactional
	public void removeFavoriteTheatre(int id) {
		this.favoriteTheatreDAO.removeFavoriteTheatre(id);
	}
        	
}
