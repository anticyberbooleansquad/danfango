/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author johnlegutko
 */
//@Entity
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String movieName;
    private Long movieID;
    private Date publishDate;
    private Long agencyID;
    private Long agencyArticleID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Long agencyID) {
        this.agencyID = agencyID;
    }

    public Long getAgencyArticleID() {
        return agencyArticleID;
    }

    public void setAgencyArticleID(Long agencyArticleID) {
        this.agencyArticleID = agencyArticleID;
    }
    
    
    
    
}
