package webstore.service;

import webstore.domain.Order;

public interface OrderService {
    void processOrder(Order order);

    Long saveOrder(Order order);
}
