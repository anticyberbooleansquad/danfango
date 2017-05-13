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
import Model.Movie;
import Model.Showing;
import Model.Theatre;
import Model.TheatreRoom;
import Model.TheatreShowings;
import java.sql.Timestamp;

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
	public List<Integer> getMovieIdsByTheatre(Theatre theatre) {
		return this.showingDAO.getMovieIdsByTheatre(theatre);
	}
        
	@Transactional
	public Showing getShowingById(int id) {
		return this.showingDAO.getShowingById(id);
	}
        
        @Transactional
        public Showing getShowingByJoe(Movie movie, Theatre theatre, Timestamp time){
            return this.showingDAO.getShowingByJoe(movie, theatre, time);
        }
        
        @Transactional
        public List<Showing> getShowingByTimestamp(){
            return this.showingDAO.getShowingByTimestamp();
        }
        
        @Transactional
        public List<Showing> getShowingByMovieAndTheatre(Movie movie, Theatre theatre){
            return this.showingDAO.getShowingByMovieAndTheatre(movie, theatre);
        }
        
        @Transactional
        public List<Showing> getShowingByMovie(Movie movie){
            return this.showingDAO.getShowingByMovie(movie);
        }
        
        @Transactional
        public List<Showing> getShowingByTheatre(Theatre theatre){
            return this.showingDAO.getShowingByTheatre(theatre);
        }
	
	@Transactional
	public void removeShowing(int id) {
		this.showingDAO.removeShowing(id);
	}
	
}
