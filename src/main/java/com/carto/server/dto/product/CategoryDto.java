package com.carto.server.dto.product;

import com.carto.server.model.ProductCategory;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String text;
    private String key;
    private String img;

    public void convertToDto(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.text = productCategory.getText();
        this.key = productCategory.getKey();
        this.img = productCategory.getImg();
    }
}
