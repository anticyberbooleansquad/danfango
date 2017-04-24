/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.CrewMemberDAO;
import Model.CrewMember;
import java.sql.Timestamp;

@Service
public class CrewMemberService {

	private CrewMemberDAO crewMemberDAO;

	public void setCrewMemberDAO(CrewMemberDAO crewMemberDAO) {
		this.crewMemberDAO = crewMemberDAO;
	}

	
	@Transactional
	public void addCrewMember(CrewMember c) {
		this.crewMemberDAO.addCrewMember(c);
	}

	
	@Transactional
	public void updateCrewMember(CrewMember c) {
		this.crewMemberDAO.updateCrewMember(c);
	}

	
	@Transactional
	public List<CrewMember> listCrewMembers() {
		return this.crewMemberDAO.listCrewMembers();
	}

	
	@Transactional
	public CrewMember getCrewMemberById(int id) {
		return this.crewMemberDAO.getCrewMemberById(id);
	}
        
        @Transactional
	public CrewMember getCrewMemberByNameAndDOB(String name, Timestamp dob) {
		return this.crewMemberDAO.getCrewMemberByNameAndDOB(name, dob);
	}
	
	@Transactional
	public void removeCrewMember(int id) {
		this.crewMemberDAO.removeCrewMember(id);
	}
        
                @Transactional
                public List<CrewMember> getCrewMembersLikeName(String name){
                    return this.crewMemberDAO.getCrewMembersLikeName(name);
                }
	
}
