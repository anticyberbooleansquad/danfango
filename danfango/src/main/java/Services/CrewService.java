/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.CrewDAO;
import Model.CrewMember;

@Service
public class CrewService {

	private CrewDAO crewDAO;

	public void setCrewDao(CrewDAO crewDAO) {
		this.crewDAO = crewDAO;
	}

	
	@Transactional
	public void addCrew(CrewMember u) {
		this.crewDAO.addCrew(u);
	}

	
	@Transactional
	public void updateCrew(CrewMember u) {
		this.crewDAO.updateCrew(u);
	}

	
	@Transactional
	public List<CrewMember> listCrews() {
		return this.crewDAO.listCrews();
	}

	
	@Transactional
	public CrewMember getCrewById(int id) {
		return this.crewDAO.getCrewById(id);
	}
        
        @Transactional
	public CrewMember getCrewByAgencyId(String agencyId) {
		return this.crewDAO.getCrewByAgencyId(agencyId);
	}
	
	@Transactional
	public void removeCrew(int id) {
		this.crewDAO.removeCrew(id);
	}
	
}
