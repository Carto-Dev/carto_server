package com.carto.server.modules.order;

import com.carto.server.dto.order.CreateOrderDto;
import com.carto.server.dto.order.CreateOrderItemDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Order;
import com.carto.server.model.OrderItem;
import com.carto.server.model.Product;
import com.carto.server.modules.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public Order createOrder(CartoUser cartoUser, CreateOrderDto createOrderDto) throws NotFoundException {
        Order order = new Order(null, cartoUser, new HashSet<>(), null, null);

        for (CreateOrderItemDto itemDto : createOrderDto.getOrders()) {
            Product product = this.productService.fetchProductById(itemDto.getProductId());

            order.getOrders().add(new OrderItem(null, itemDto.getQuantity(), product, order));
        }

        return this.orderRepository.save(order);
    }

    @Override
    public Set<Order> fetchOrdersByUser(CartoUser cartoUser) {
        return cartoUser.getOrders();
    }

}
