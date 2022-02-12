package com.carto.server.modules.review;

import com.carto.server.dto.review.DeleteReviewDto;
import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.dto.review.UpdateReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;

import java.util.Set;

public interface ReviewService {

    public Set<Review> fetchReviewsByUser(CartoUser cartoUser);

    public Review createReview(CartoUser cartoUser, NewReviewDto newReviewDto) throws NotFoundException;

    public Review updateReview(CartoUser cartoUser, UpdateReviewDto updateReviewDto) throws NotFoundException;

    public void deleteReview(CartoUser cartoUser, DeleteReviewDto deleteReviewDto) throws NotFoundException;

}
