/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.CrewMemberMovieDAO;
import Model.CrewMemberMovie;
import Model.CrewMember;
import Model.Movie;

@Service
public class CrewMemberMovieService {

	private CrewMemberMovieDAO crewMemberMovieDAO;

	public void setCrewMemberMovieDAO(CrewMemberMovieDAO crewMemberMovieDAO) {
		this.crewMemberMovieDAO = crewMemberMovieDAO;
	}

	
	@Transactional
	public void addCrewMemberMovie(CrewMemberMovie u) {
		this.crewMemberMovieDAO.addCrewMemberMovie(u);
	}

	
	@Transactional
	public void updateCrewMemberMovie(CrewMemberMovie u) {
		this.crewMemberMovieDAO.updateCrewMemberMovie(u);
	}

	
	@Transactional
	public List<CrewMemberMovie> listCrewMemberMovies() {
		return this.crewMemberMovieDAO.listCrewMemberMovies();
	}

	
	@Transactional
	public CrewMemberMovie getCrewMemberMovieById(int id) {
		return this.crewMemberMovieDAO.getCrewMemberMovieById(id);
	}
        
        @Transactional
	public List<CrewMemberMovie> getCrewMemberMovieByCrewMember(CrewMember crewMember) {
		return this.crewMemberMovieDAO.getCrewMemberMovieByCrewMember(crewMember);
	}
        
        @Transactional
	public List<CrewMemberMovie> getCrewMemberMovieByCrewMember(Movie movie) {
		return this.crewMemberMovieDAO.getCrewMemberMovieByMovie(movie);
	}
	
	@Transactional
	public void removeCrewMemberMovie(int id) {
		this.crewMemberMovieDAO.removeCrewMemberMovie(id);
	}
        	
}
