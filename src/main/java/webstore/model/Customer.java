package webstore.model;

public class Customer {
    private Integer customerId;
    private String name;
    private String address;
    private Integer noOfOrdersMade;

    public Customer(Integer customerId, String name, String address, Integer noOfOrdersMade) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.noOfOrdersMade = noOfOrdersMade;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNoOfOrdersMade() {
        return noOfOrdersMade;
    }

    public void setNoOfOrdersMade(Integer noOfOrdersMade) {
        this.noOfOrdersMade = noOfOrdersMade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (getCustomerId() != null ? !getCustomerId().equals(customer.getCustomerId()) : customer.getCustomerId() != null)
            return false;
        if (getName() != null ? !getName().equals(customer.getName()) : customer.getName() != null) return false;
        if (getAddress() != null ? !getAddress().equals(customer.getAddress()) : customer.getAddress() != null)
            return false;
        return getNoOfOrdersMade() != null ? getNoOfOrdersMade().equals(customer.getNoOfOrdersMade()) : customer.getNoOfOrdersMade() == null;
    }

    @Override
    public int hashCode() {
        int result = getCustomerId() != null ? getCustomerId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getNoOfOrdersMade() != null ? getNoOfOrdersMade().hashCode() : 0);
        return result;
    }
}
