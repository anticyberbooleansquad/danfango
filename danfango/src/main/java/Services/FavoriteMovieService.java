/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.FavoriteMovieDAO;
import Model.FavoriteMovie;
import Model.Movie;
import Model.User;

@Service
public class FavoriteMovieService {

	private FavoriteMovieDAO favoriteMovieDAO;

	public void setFavoriteMovieDAO(FavoriteMovieDAO favoriteMovieDAO) {
		this.favoriteMovieDAO = favoriteMovieDAO;
	}

	
	@Transactional
	public void addFavoriteMovie(FavoriteMovie u) {
		this.favoriteMovieDAO.addFavoriteMovie(u);
	}

	
	@Transactional
	public void updateFavoriteMovie(FavoriteMovie u) {
		this.favoriteMovieDAO.updateFavoriteMovie(u);
	}

	
	@Transactional
	public List<FavoriteMovie> listFavoriteMovies() {
		return this.favoriteMovieDAO.listFavoriteMovies();
	}

	
	@Transactional
	public FavoriteMovie getFavoriteMovieById(int id) {
		return this.favoriteMovieDAO.getFavoriteMovieById(id);
	}
        
        @Transactional
	public FavoriteMovie getFavoriteMovieByUserAndMovie(User user, Movie movie) {
		return this.favoriteMovieDAO.getFavoriteMovieByUserAndMovie(user, movie);
	}
	
         @Transactional
	public List<FavoriteMovie> getFavoriteMoviesByUser(User user) {
		return this.favoriteMovieDAO.getFavoriteMoviesByUser(user);
	}
	
        
	@Transactional
	public void removeFavoriteMovie(int id) {
		this.favoriteMovieDAO.removeFavoriteMovie(id);
	}
        	
}
