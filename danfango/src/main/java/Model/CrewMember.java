/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import javax.persistence.*;
/**
 *
 * @author johnlegutko
 */
//@Entity
public class CrewMember {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Agency agency;
    private int agencyCrewId;
    private String firstName;
    private String lastName;
    private String biography;
    private int age;
    private Date dob;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the crewMemberId to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the agencyId
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     * @param agencyId the agencyId to set
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    /**
     * @return the agencyCrewId
     */
    public int getAgencyCrewId() {
        return agencyCrewId;
    }

    /**
     * @param agencyCrewId the agencyCrewId to set
     */
    public void setAgencyCrewId(int agencyCrewId) {
        this.agencyCrewId = agencyCrewId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    
    
}
