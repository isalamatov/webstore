package webstore.repository;

import webstore.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);
}
