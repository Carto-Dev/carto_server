package com.carto.server.modules.order;

import com.carto.server.dto.order.CreateOrderDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Order;

public interface OrderService {

    public Order createOrder(CartoUser cartoUser, CreateOrderDto createOrderDto) throws NotFoundException;

}
