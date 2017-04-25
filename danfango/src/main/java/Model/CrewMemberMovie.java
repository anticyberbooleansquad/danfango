/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author johnlegutko
 */
@Entity
@Table
public class CrewMemberMovie implements Serializable{
    
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private Integer id;
    @OneToOne
    private CrewMember crewMember;
    @OneToOne
    private Movie movie;

    public CrewMemberMovie(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
     
}
