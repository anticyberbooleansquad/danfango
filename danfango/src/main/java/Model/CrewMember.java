/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author johnlegutko
 */
@Entity
@Table
public class CrewMember implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Agency agency;
    private int agencyCrewId;
    private String fullName;
    
    @Column(name = "biography", nullable = false, length = 10000)
    private String biography ;
    private int age;
    private Timestamp dob;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public Timestamp getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Timestamp dob) {
        this.dob = dob;
    }
    
    
    
}
