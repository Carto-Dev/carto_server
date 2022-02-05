package com.carto.server.modelDtos;

import com.carto.server.model.ReviewImage;
import lombok.Data;

@Data
public class ReviewImageDto {

    private Long id;
    private String image;

    public void convertToDto(ReviewImage reviewImage) {
        this.id = reviewImage.getId();
        this.image = reviewImage.getImg();
    }

}
