/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.MovieDAO;
import Model.Movie;

@Service
public class MovieService {

	private MovieDAO movieDAO;

	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	
	@Transactional
	public void addMovie(Movie u) {
		this.movieDAO.addMovie(u);
	}

	
	@Transactional
	public void updateMovie(Movie u) {
		this.movieDAO.updateMovie(u);
	}

	
	@Transactional
	public List<Movie> listMovies() {
		return this.movieDAO.listMovies();
	}

	
	@Transactional
	public Movie getMovieById(int id) {
		return this.movieDAO.getMovieById(id);
	}
	
	@Transactional
	public void removeMovie(int id) {
		this.movieDAO.removeMovie(id);
	}
	
}
