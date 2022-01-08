package com.carto.server.modules.review;

import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CartoUser cartoUser, NewReviewDto newReviewDto) {

    }
}
