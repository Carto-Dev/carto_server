package com.carto.server.modules.review;

import com.carto.server.dto.review.NewReviewDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import com.carto.server.model.Review;
import com.carto.server.modules.product.ProductRepository;
import com.carto.server.modules.user.CartoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

            Review review = new Review(null, newReviewDto.getText(), newReviewDto.getStars(), product, cartoUser);

            return this.reviewRepository.save(review);
        }

    }
}