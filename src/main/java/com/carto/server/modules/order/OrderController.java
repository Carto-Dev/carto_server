package com.carto.server.modules.order;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.order.CreateOrderDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Order;
import com.carto.server.modelDtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody CreateOrderDto createOrderDto) throws NotFoundException {
        Order order = this.orderService.createOrder(cartoUser, createOrderDto);

        OrderDto orderDto = new OrderDto();
        orderDto.convertToDto(order);

        return orderDto;
    }

}
