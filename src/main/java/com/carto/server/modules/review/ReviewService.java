package com.carto.server.modules.review;

import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;

public interface ReviewService {

    public Review createReview(CartoUser cartoUser, NewReviewDto newReviewDto) throws NotFoundException;

}
