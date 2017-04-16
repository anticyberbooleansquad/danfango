/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.AdvertisementDAO;
import Model.Advertisement;

@Service
public class AdvertisementService {

	private AdvertisementDAO advertisementDAO;

	public void setAdvertisementDAO(AdvertisementDAO advertisementDAO) {
		this.advertisementDAO = advertisementDAO;
	}

	
	@Transactional
	public void addAdvertisement(Advertisement a) {
		this.advertisementDAO.addAdvertisement(a);
	}

	
	@Transactional
	public void updateAdvertisement(Advertisement a) {
		this.advertisementDAO.updateAdvertisement(a);
	}

	
	@Transactional
	public List<Advertisement> listAdvertisements() {
		return this.advertisementDAO.listAdvertisements();
	}

	
	@Transactional
	public Advertisement getAdvertisementById(int id) {
		return this.advertisementDAO.getAdvertisementById(id);
	}
	
	@Transactional
	public void removeAdvertisement(int id) {
		this.advertisementDAO.removeAdvertisement(id);
	}
	
}
