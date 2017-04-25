/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.MovieTrailerDAO;
import Model.MovieTrailer;
import Model.CrewMember;
import Model.Movie;

@Service
public class MovieTrailerService {

	private MovieTrailerDAO movieTrailerDAO;

	public void setMovieTrailerDAO(MovieTrailerDAO movieTrailerDAO) {
		this.movieTrailerDAO = movieTrailerDAO;
	}

	
	@Transactional
	public void addMovieTrailer(MovieTrailer u) {
		this.movieTrailerDAO.addMovieTrailer(u);
	}

	
	@Transactional
	public void updateMovieTrailer(MovieTrailer u) {
		this.movieTrailerDAO.updateMovieTrailer(u);
	}

	
	@Transactional
	public List<MovieTrailer> listMovieTrailers() {
		return this.movieTrailerDAO.listMovieTrailers();
	}

	
	@Transactional
	public MovieTrailer getMovieTrailerById(int id) {
		return this.movieTrailerDAO.getMovieTrailerById(id);
	}
        
        @Transactional
	public List<MovieTrailer> getMovieTrailerByMovie(Movie movie) {
		return this.movieTrailerDAO.getMovieTrailerByMovie(movie);
	}
	
	@Transactional
	public void removeMovieTrailer(int id) {
		this.movieTrailerDAO.removeMovieTrailer(id);
	}
        	
}
