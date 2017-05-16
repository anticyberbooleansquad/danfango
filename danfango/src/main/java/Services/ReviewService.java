/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.ReviewDAO;
import Model.Movie;
import Model.Review;

@Service
public class ReviewService {

	private ReviewDAO reviewDAO;

	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}

	
	@Transactional
	public void addReview(Review r) {
		this.reviewDAO.addReview(r);
	}

	
	@Transactional
	public void updateReview(Review r) {
		this.reviewDAO.updateReview(r);
	}

	
	@Transactional
	public List<Review> listReviews() {
		return this.reviewDAO.listReviews();
	}

	
	@Transactional
	public Review getReviewById(int id) {
		return this.reviewDAO.getReviewById(id);
	}
        
        @Transactional
        public List<Review> getReviewsByMovie(Movie movie)
        {
            return this.reviewDAO.getReviewsByMovie(movie);
        }
	
	@Transactional
	public void removeReview(int id) {
		this.reviewDAO.removeReview(id);
	}
	
}
