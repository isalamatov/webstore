package webstore.domain;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Validated
public class ShippingDetail implements Serializable {
    private static final long serialVersionUID = 6350930334140807514L;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String shippingDate;
    private Address shippingAddress;

    public ShippingDetail() {
        this.shippingAddress = new Address();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void validateCollectShippingDetail(ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        try {
            LocalDate shippingDate = LocalDate.parse(getShippingDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            messages.addMessage(new MessageBuilder().error().source("shipping date")
                    .defaultText("Date should be in ISO Local Date formatt" + DateTimeFormatter.ISO_LOCAL_DATE.toString())
                    .build());        }
        LocalDate shippingDate = LocalDate.parse(getShippingDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        if (name.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("name").defaultText("Name should not be empty")
                    .build());
        }
        if (shippingAddress.equals(null)) {
            messages.addMessage(new MessageBuilder().error().source("name").defaultText("Address should not be empty")
                    .build());
        }
        if (shippingDate.isBefore(LocalDate.now())) {
            messages.addMessage(new MessageBuilder().error().source("shipping date").
                    defaultText("Shipping date must be a future date").build());
        }
    }
}