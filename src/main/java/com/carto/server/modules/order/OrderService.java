package com.carto.server.modules.order;

import com.carto.server.dto.order.CreateOrderDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Order;

import java.util.Set;

public interface OrderService {

    public Order createOrder(CartoUser cartoUser, CreateOrderDto createOrderDto) throws NotFoundException;

    public Set<Order> fetchOrdersByUser(CartoUser cartoUser);

}
