package me.greeenly.orderservice.service;


import me.greeenly.orderservice.domain.Order;
import me.greeenly.orderservice.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrdersByUserId(String userId);

}
