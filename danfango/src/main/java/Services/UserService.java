/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.UserDAO;
import Model.User;

@Service
public class UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	@Transactional
	public void addUser(User u) {
		this.userDAO.addUser(u);
	}

	
	@Transactional
	public void updateUser(User u) {
		this.userDAO.updateUser(u);
	}

	
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	
	@Transactional
	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}
	
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}
	
}
