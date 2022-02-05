package com.carto.server.modelDtos;

import com.carto.server.model.Review;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReviewDto {

    private Long id;
    private String text;
    private Long stars;
    private UserDto user;
    private List<ReviewImageDto> images;

    public void convertToDto(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        this.stars = review.getStars();

        UserDto userDto = new UserDto();
        userDto.convertToDto(review.getUser());

        this.user = userDto;
        this.images = review.getImgLinks().stream().map(img -> {
            ReviewImageDto reviewImageDto = new ReviewImageDto();
            reviewImageDto.convertToDto(img);

            return reviewImageDto;
        }).collect(Collectors.toList());
    }

}
