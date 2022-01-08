package com.carto.server.modules.review;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;
import com.carto.server.modelDtos.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto createReview(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody NewReviewDto newReviewDto) throws NotFoundException {
        Review savedReview = this.reviewService.createReview(cartoUser, newReviewDto);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.convertToDto(savedReview);

        return reviewDto;
    }

}
