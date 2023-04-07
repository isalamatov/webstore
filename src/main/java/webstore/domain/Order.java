package webstore.domain;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;

import java.io.Serializable;
public class Order implements Serializable{
    private static final long serialVersionUID = -3560539622417210365L;
    private Long orderId;
    private Cart cart;
    private Customer customer;
    private ShippingDetail shippingDetail;
    public Order() {
        this.customer = new Customer();
        this.shippingDetail = new ShippingDetail();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    public void validateCollectCustomerInfo(ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (getCustomer().getName().isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("name").defaultText("Name should not be empty")
                    .build());
        }
        if (getCustomer().getBillingAddress().equals(null)) {
            messages.addMessage(new MessageBuilder().error().source("name").defaultText("Address should not be empty")
                    .build());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (getCart() != null ? !getCart().equals(order.getCart()) : order.getCart() != null) return false;
        if (getCustomer() != null ? !getCustomer().equals(order.getCustomer()) : order.getCustomer() != null)
            return false;
        return getShippingDetail() != null ? getShippingDetail().equals(order.getShippingDetail()) : order.getShippingDetail() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getCart() != null ? getCart().hashCode() : 0);
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getShippingDetail() != null ? getShippingDetail().hashCode() : 0);
        return result;
    }
}