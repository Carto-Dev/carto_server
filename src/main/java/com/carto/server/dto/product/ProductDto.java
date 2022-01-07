package com.carto.server.dto.product;

import com.carto.server.dto.user.UserDto;
import com.carto.server.model.Product;
import com.carto.server.model.ProductImage;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDto {
    private Long id;
    private UserDto user;
    private String title;
    private String description;
    private double cost;
    private List<String> images;
    private List<CategoryDto> categories;

    public void convertToDto(Product product) {
        this.id = product.getId();

        UserDto userDto = new UserDto();
        userDto.convertToDto(product.getUser());

        this.user = userDto;
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.cost = product.getCost();
        this.images = product.getImgLinks().stream().map(ProductImage::getImg).collect(Collectors.toList());
        this.categories = product.getCategories().stream().map(productCategory -> {
            CategoryDto categoryDto = new CategoryDto();

            categoryDto.convertToDto(productCategory);

            return categoryDto;
        }).collect(Collectors.toList());
    }
}
