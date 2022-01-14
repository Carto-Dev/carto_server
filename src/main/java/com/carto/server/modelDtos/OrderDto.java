package com.carto.server.modelDtos;

import com.carto.server.model.Order;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private Set<OrderItemDto> orders;
    private Date createdAt;
    private Date updatedAt;

    public void convertToDto(Order order) {
        this.id = order.getId();

        UserDto userDto = new UserDto();
        userDto.convertToDto(order.getUser());
        this.user = userDto;

        this.orders = order.getOrders().stream().map(dbOrder -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.convertToDto(dbOrder);

            return orderItemDto;
        }).collect(Collectors.toSet());

        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

}
