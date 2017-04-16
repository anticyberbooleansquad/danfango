/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class Movie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    private int agencyId;
    private String agencyMovieId;
    private String title;
    private rating rating;
    private enum rating {G, PG, PG_13, R, NC_17};
    private double movieScore;
    private Timestamp releaseDate;
    private String synopsis;
    private String runTime;
    private String poster;
    //private List<CrewMember> crewMembers;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the agencyId
     */
    public int getAgencyId() {
        return agencyId;
    }

    /**
     * @param agencyId the agencyId to set
     */
    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * @return the agencyMovieId
     */
    public String getAgencyMovieId() {
        return agencyMovieId;
    }

    /**
     * @param agencyMovieId the agencyMovieId to set
     */
    public void setAgencyMovieId(String agencyMovieId) {
        this.agencyMovieId = agencyMovieId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * @param synopsis the synopsis to set
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    public void setMovieScore(double movieScore) {
        this.movieScore = movieScore;
    }
    
    public double getMovieScore() {
        return movieScore;
    }
    
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    
    public String getRunTime() {
        return runTime;
    }
    
    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public String getPoster() {
        return poster;
    }
    
    public void setRating() {
        this.rating=rating.G;
    }
    

//    /**
//     * @return the crewMembers
//     */
//    public List<CrewMember> getCrewMembers() {
//        return crewMembers;
//    }
//
//    /**
//     * @param crewMembers the crewMembers to set
//     */
//    public void setCrewMembers(List<CrewMember> crewMembers) {
//        this.crewMembers = crewMembers;
//    }
    
    
}
