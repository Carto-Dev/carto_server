package com.carto.server.modelDtos;

import com.carto.server.model.ProductImage;
import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String image;

    public void convertToDto(ProductImage productImage) {
        this.id = productImage.getId();
        this.image = productImage.getImg();
    }
}
