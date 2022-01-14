package com.carto.server.modelDtos;

import com.carto.server.model.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private String id;
    private ProductDto product;
    private Long quantity;

    public void convertToDto(OrderItem orderItem) {
        this.id = orderItem.getId();

        ProductDto productDto = new ProductDto();
        productDto.convertToDto(orderItem.getProduct());
        this.product = productDto;

        this.quantity = orderItem.getQuantity();
    }

}
