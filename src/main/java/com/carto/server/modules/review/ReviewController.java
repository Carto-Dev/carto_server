package com.carto.server.modules.review;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.review.DeleteReviewDto;
import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.dto.review.UpdateReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;
import com.carto.server.modelDtos.ReviewDto;
import com.carto.server.modelDtos.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<UserReview> fetchReviewsByUser(@LoggedInUser CartoUser cartoUser) {
        Set<Review> userReviews = this.reviewService.fetchReviewsByUser(cartoUser);

        return userReviews.stream().map(review -> {
            UserReview userReview = new UserReview();
            userReview.convertToDto(review);

            return userReview;
        }).collect(Collectors.toList());
    }

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
