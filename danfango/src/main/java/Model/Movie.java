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
    
    //private enum rating {G, PG, PG_13, R, NC_17};
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @OneToOne
    private Agency agency;
    private String imdbID;
    private String tmdbID;
    private String title;
    private String rating;
    private double movieScore;
    private Timestamp releaseDate;
    @Column(name = "synopsis", nullable = false, length = 10000)
    private String synopsis;
    private String runTime;
    private String poster;
    private String backdrop;

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
   
    /**
     * @return the rating
     */
    public String getRating() {
      return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
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

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTmdbID() {
        return tmdbID;
    }

    public void setTmdbID(String tmdbID) {
        this.tmdbID = tmdbID;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

//    public List<Trailer> getTrailers() {
//        return trailers;
//    }
//
//    public void setTrailers(List<Trailer> trailers) {
//        this.trailers = trailers;
//    }
//    

  
}
