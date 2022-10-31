package webstore.service;

import webstore.domain.Order;

public interface OrderService {
    void processOrder(String productId, long count);

    Long saveOrder(Order order);
}
