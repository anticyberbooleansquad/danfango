/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.MovieGenreDAO;
import Model.Genre;
import Model.Movie;
import Model.MovieGenre;

@Service
public class MovieGenreService {

	private MovieGenreDAO movieGenreDAO;

	public void setMovieGenreDAO(MovieGenreDAO movieGenreDAO) {
		this.movieGenreDAO = movieGenreDAO;
	}

	
	@Transactional
	public void addMovieGenre(MovieGenre u) {
		this.movieGenreDAO.addMovieGenre(u);
	}

	
	@Transactional
	public void updateMovieGenre(MovieGenre u) {
		this.movieGenreDAO.updateMovieGenre(u);
	}

	
	@Transactional
	public List<MovieGenre> listMovieGenres() {
		return this.movieGenreDAO.listMovieGenres();
	}

	
	@Transactional
	public MovieGenre getMovieGenreById(int id) {
		return this.movieGenreDAO.getMovieGenreById(id);
	}
        
        @Transactional
	public List<MovieGenre> getMovieGenresByMovie(Movie movie) {
		return this.movieGenreDAO.getMovieGenresByMovie(movie);
	}
        
        @Transactional
	public List<MovieGenre> getMovieGenresByGenre(Genre genre) {
		return this.movieGenreDAO.getMovieGenresByGenre(genre);
	}
        
        @Transactional
	public MovieGenre getMovieGenresByGenreAndMovie(Genre genre, Movie movie) {
		return this.movieGenreDAO.getMovieGenresByGenreAndMovie(genre, movie);
	}
       
	@Transactional
	public void removeMovieGenre(int id) {
		this.movieGenreDAO.removeMovieGenre(id);
	}
        	
}
