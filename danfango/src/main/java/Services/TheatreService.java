/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.TheatreDAO;
import Model.Theatre;
import java.util.ArrayList;

@Service
public class TheatreService {

    private TheatreDAO theatreDAO;

    public void setTheatreDAO(TheatreDAO theatreDAO) {
        this.theatreDAO = theatreDAO;
    }

    @Transactional
    public void addTheatre(Theatre u) {
        this.theatreDAO.addTheatre(u);
    }

    @Transactional
    public void updateTheatre(Theatre u) {
        this.theatreDAO.updateTheatre(u);
    }

    @Transactional
    public List<Theatre> listTheatres() {
        return this.theatreDAO.listTheatres();
    }
    
    @Transactional
    public List<Integer> getTheatreIds() {
        return this.theatreDAO.getTheatreIds();
    }

    @Transactional
    public Theatre getTheatreById(int id) {
        return this.theatreDAO.getTheatreById(id);
    }

    @Transactional
    public void removeTheatre(int id) {
        this.theatreDAO.removeTheatre(id);
    }

    @Transactional
    public Theatre getTheatreByAgencyTheatreId(int id) {
        return this.theatreDAO.getTheatreByAgencyTheatreId(id);
    }
    
    @Transactional
    public Theatre getTheatreByName(String name) {
        return this.theatreDAO.getTheatreByName(name);
    }
    
    @Transactional
    public List<Theatre> getTheatresByZip(String zipcode) {
        return this.theatreDAO.getTheatresByZip(zipcode);
    }

    @Transactional
    public List<Theatre> getTheatresLikeName(String name) {
        return this.theatreDAO.getTheatresLikeName(name);
    }

    @Transactional
    public List<Theatre> getTheatresInZipList(ArrayList<String> zipcodes) {
        return this.theatreDAO.getTheatresInZipList(zipcodes);
    }

    @Transactional
    public List<Theatre> getTheatresByState(String state) {
        return this.theatreDAO.getTheatresByState(state);
    }

    @Transactional
    public List<Theatre> getTheatresLikeCityByState(String city, String state) {
        return this.theatreDAO.getTheatresLikeCityByState(city, state);
    }

    @Transactional
    public List<Theatre> getTheatresLikeCityAndLikeState(String city, String state) {
        return this.theatreDAO.getTheatresLikeCityAndLikeState(city, state);
    }

    @Transactional
    public List<Theatre> getTheatresLikeCityOrLikeState(String substring) {
        List<Theatre> theatresByLikeCity = getTheatresLikeCity(substring);
        List<Theatre> theatresByLikeState = getTheatresLikeState(substring);
        List<Theatre> theatres = new ArrayList();
        theatres.addAll(theatresByLikeCity);
        theatres.addAll(theatresByLikeState);
        return theatres;
    }

    @Transactional
    public List<Theatre> getTheatresLikeCity(String city) {
        return this.theatreDAO.getTheatresLikeCity(city);
    }

    @Transactional
    public List<Theatre> getTheatresLikeState(String state) {
        return this.theatreDAO.getTheatresLikeState(state);
    }

}
