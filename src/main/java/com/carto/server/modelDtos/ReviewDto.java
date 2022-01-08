package com.carto.server.modelDtos;

import com.carto.server.model.Review;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;
    private String text;
    private Long stars;
    private UserDto user;

    public void convertToDto(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        this.stars = review.getId();

        UserDto userDto = new UserDto();
        userDto.convertToDto(review.getUser());

        this.user = userDto;
    }

}
