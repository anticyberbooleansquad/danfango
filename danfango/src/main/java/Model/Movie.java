/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    private enum rating {G, PG, PG_13, R, NC_17};
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @OneToOne
    private Agency agency;
    private String agencyMovieId;
    private String title;
    private rating rating;
    private double movieScore;
    private Timestamp releaseDate;
    private String synopsis;
    private String runTime;
    private String poster;
    @ManyToMany
    private List<CrewMember> crewMembers = new ArrayList<CrewMember>();
    @OneToMany
    private List<Genre> genres;

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
      this.setRating(getRating().G);
    }
    

    /**
     * @return the crewMembers
     */
    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    /**
     * @param crewMembers the crewMembers to set
     */
    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

  /**
   * @return the rating
   */
  public rating getRating() {
    return rating;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(rating rating) {
    this.rating = rating;
  }

  /**
   * @return the genres
   */
  public List<Genre> getGenres() {
    return genres;
  }

  /**
   * @param genres the genres to set
   */
  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }
    
    
}
