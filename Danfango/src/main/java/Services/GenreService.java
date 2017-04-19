/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.GenreDAO;
import Model.Genre;

@Service
public class GenreService {

	private GenreDAO genreDAO;

	public void setGenreDAO(GenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}

	
	@Transactional
	public void addGenre(Genre g) {
		this.genreDAO.addGenre(g);
	}

	
	@Transactional
	public void updateGenre(Genre g) {
		this.genreDAO.updateGenre(g);
	}

	
	@Transactional
	public List<Genre> listGenres() {
		return this.genreDAO.listGenres();
	}

	
	@Transactional
	public Genre getGenreById(int id) {
		return this.genreDAO.getGenreById(id);
	}
        
        @Transactional
	public Genre getGenreByName(String name) {
		return this.genreDAO.getGenreByName(name);
	}
	
	@Transactional
	public void removeGenre(int id) {
		this.genreDAO.removeGenre(id);
	}
        	
}
