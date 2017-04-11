/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Set;

/**
 *
 * @author johnlegutko
 */
public class Theatre {
    
    private int id;
    private Agency agency;
    private int agencyTheatreId;
    private String name;
    private String address;
    private String city;
    private String zip;
    private String state;
    private Set<Showing> showings;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the agency
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     * @param agency the agency to set
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    /**
     * @return the agencyTheatreId
     */
    public int getAgencyTheatreId() {
        return agencyTheatreId;
    }

    /**
     * @param agencyTheatreId the agencyTheatreId to set
     */
    public void setAgencyTheatreId(int agencyTheatreId) {
        this.agencyTheatreId = agencyTheatreId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the showings
     */
    public Set<Showing> getShowings() {
        return showings;
    }

    /**
     * @param showings the showings to set
     */
    public void setShowings(Set<Showing> showings) {
        this.showings = showings;
    }
    
}
