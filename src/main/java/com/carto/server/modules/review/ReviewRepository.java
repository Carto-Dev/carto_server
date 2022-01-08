package com.carto.server.modules.review;

import com.carto.server.model.CartoUser;
import com.carto.server.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndUser(Long id, CartoUser cartoUser);

}
