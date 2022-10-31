package webstore.repository.impl;

import org.springframework.stereotype.Repository;
import webstore.domain.Cart;
import webstore.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final Map<String, Cart> listOfCarts;

    public InMemoryCartRepository() {
        listOfCarts = new HashMap<String, Cart>();
    }

    public Cart create(Cart cart) {
        if (listOfCarts.containsKey(cart.getCartId())) {
            throw new IllegalArgumentException(String.
                    format("Can not create a cart.A cart with the give id (%s) aldready exist",
                            cart.getCartId()));
        }
        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }

    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }

    public void update(String cartId, Cart cart) {
        if (!listOfCarts.containsKey(cartId)) {
            throw new IllegalArgumentException(String.
                    format("Can not update cart.The cart with the give id (%s)does not does not exist",
                            cartId));
        }
        listOfCarts.put(cartId, cart);
    }

    public void delete(String cartId) {
        if (!listOfCarts.containsKey(cartId)) {
            throw new IllegalArgumentException(String.
                    format("Can not delete cart.The cart with the give id (%s) does not does not exist",
                            cartId));
        }
        listOfCarts.remove(cartId);
    }
}