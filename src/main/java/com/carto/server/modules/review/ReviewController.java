package com.carto.server.modules.review;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.review.DeleteReviewDto;
import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.dto.review.UpdateReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;
import com.carto.server.modelDtos.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ReviewDto updateReview(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody UpdateReviewDto updateReviewDto) throws NotFoundException {
        Review savedReview = this.reviewService.updateReview(cartoUser, updateReviewDto);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.convertToDto(savedReview);

        return reviewDto;
    }

    @DeleteMapping
    public void deleteReview(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody DeleteReviewDto deleteReviewDto) throws NotFoundException {
        this.reviewService.deleteReview(cartoUser, deleteReviewDto);
    }

}
