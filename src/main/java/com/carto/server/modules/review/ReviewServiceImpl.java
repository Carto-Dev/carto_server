package com.carto.server.modules.review;

import com.carto.server.dto.review.DeleteReviewDto;
import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.dto.review.UpdateReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import com.carto.server.model.Review;
import com.carto.server.model.ReviewImage;
import com.carto.server.modules.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Override
    public Review createReview(CartoUser cartoUser, NewReviewDto newReviewDto) throws NotFoundException {

        Optional<Product> checkProduct = this.productRepository.findById(newReviewDto.getProductId());

        if (checkProduct.isEmpty()) {
            throw new NotFoundException(404, "Product Not Found");
        } else {
            Product product = checkProduct.get();
            Set<ReviewImage> reviewImages = new HashSet<>();

            Review review = new Review(null, newReviewDto.getText(), newReviewDto.getStars(), product, cartoUser, new HashSet<>());

            if (newReviewDto.getImgLinks() != null) {
                newReviewDto.getImgLinks().forEach(img -> {
                    ReviewImage reviewImage = new ReviewImage(null, img, review);

                    reviewImages.add(reviewImage);
                });

                review.setImgLinks(reviewImages);
            }
            return this.reviewRepository.save(review);
        }

    }

    @Override
    public Review updateReview(CartoUser cartoUser, UpdateReviewDto updateReviewDto) throws NotFoundException {
        Optional<Review> checkReview = this.reviewRepository.findByIdAndUser(updateReviewDto.getId(), cartoUser);

        if (checkReview.isEmpty()) {
            throw new NotFoundException(404, "Review Not Found");
        } else {
            Review review = checkReview.get();

            review.setStars(updateReviewDto.getStars());
            review.setText(updateReviewDto.getText());

            return this.reviewRepository.save(review);
        }
    }

    @Override
    public void deleteReview(CartoUser cartoUser, DeleteReviewDto deleteReviewDto) throws NotFoundException {
        Optional<Review> checkReview = this.reviewRepository.findByIdAndUser(deleteReviewDto.getId(), cartoUser);

        if (checkReview.isEmpty()) {
            throw new NotFoundException(404, "Review Not Found");
        } else {
            this.reviewRepository.delete(checkReview.get());
        }
    }
}
