package webstore.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import webstore.domain.Cart;
import webstore.domain.CartItem;
import webstore.domain.Product;
import webstore.repository.CartRepository;
import webstore.repository.ProductRepository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
@Primary
public class DBCartRepository implements CartRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ProductRepository productRepository;

    @Autowired
    public DBCartRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public Cart create(Cart cart) {
        String sqlCreateCart = "INSERT INTO carts (cart_id, product_id, quantity) VALUES (?,?,?)";
        cart.getCartItems().forEach((s, cartItem) ->
                jdbcTemplate.update(sqlCreateCart,
                        cart.getCartId(),
                        cartItem.getProduct().getProductId(),
                        cartItem.getQuantity()));
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        String sqlReadCard = "SELECT * FROM carts WHERE cart_id = ?";
        List<CartItem> cartItems = jdbcTemplate.query(sqlReadCard, (rs, rn) -> makeCartItem(rs), cartId);
        HashMap<String, CartItem> cartItemsHashMap = new HashMap<>();
        BigDecimal grandTotal = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            cartItemsHashMap.put(cartItem.getProduct().getProductId(), cartItem);
            grandTotal = grandTotal.add(cartItem.getTotalPrice());
        }
        return new Cart(cartId, cartItemsHashMap, grandTotal);
    }

    @Override
    public void update(String cartId, Cart cart) {
        delete(cartId);
        create(cart);
    }

    @Override
    public void delete(String cartId) {
        String sqlDeleteCart = "DELETE FROM carts WHERE cart_id = ?";
        jdbcTemplate.update(sqlDeleteCart, cartId);
    }

    private CartItem makeCartItem(ResultSet rs) throws SQLException {
        String productID = rs.getString("product_id");
        int quantity = rs.getInt("quantity");
        Product product = productRepository.getProductById(productID);
        BigDecimal totalPrice = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        return new CartItem(product, quantity, totalPrice);
    }
}
