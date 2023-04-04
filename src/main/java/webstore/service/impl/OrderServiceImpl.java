package webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webstore.domain.CartItem;
import webstore.domain.Order;
import webstore.domain.Product;
import webstore.repository.OrderRepository;
import webstore.repository.ProductRepository;
import webstore.service.CartService;
import webstore.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Override
    public void processOrder(Order order) {
        List<CartItem> CartItems = new ArrayList<>(order.getCart().getCartItems().values());
        for (CartItem cartItem : CartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            if (product.getUnitsInStock() < quantity) {
                throw new IllegalArgumentException("Out of Stock. Available Units in stock" + product.getUnitsInStock());
            }
            product.setUnitsInStock(product.getUnitsInStock() - quantity);
            productRepository.update(product);
        }
    }

    @Override
    public Long saveOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        cartService.delete(order.getCart().getCartId());
        return orderId;
    }
}
