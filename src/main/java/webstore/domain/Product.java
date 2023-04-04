package webstore.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import webstore.validator.ProductId;
import webstore.validator.ProductImage;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@XmlRootElement
public class Product implements Serializable {
    private static final long serialVersionUID = -8921011478183193345L;
    @Pattern(regexp = "P[0-9]+", message = "{Pattern.Product.productId.validation}")
    @ProductId
    private String productId;
    @Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
    private String name;
    @Min(value = 0, message = "{Min.Product.unitPrice.validation}")
    @Digits(integer = 8, fraction = 2, message = "{Digits.Product.unitPrice. validation}")
    @NotNull(message = "{NotNull.Product.unitPrice.validation}")
    private BigDecimal unitPrice;
    @NotNull(message = "{NotNull.Product.description.validation}")
    private String description;
    private String manufacturer;
    private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discounted;
    private String condition;

    @JsonIgnore
    @ProductImage(size = 10240000L)
    private MultipartFile productImage;

    @JsonIgnore
    private MultipartFile productManual;


    public Product() {
        super();
        this.productImage = new MockMultipartFile("file", new byte[] {0,0});
    }

    public Product(String productId, String name, BigDecimal
            unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.productImage = new MockMultipartFile("file", new byte[] {0,0});
    }

    public Product(String productId, String name, BigDecimal unitPrice, String description, String manufacturer,
                   String category, long unitsInStock, long unitsInOrder, boolean discounted, String condition) {
         this.productId = productId;
         this.name = name;
         this.unitPrice = unitPrice;
         this.description = description;
         this.manufacturer = manufacturer;
         this.category = category;
         this.unitsInStock = unitsInStock;
         this.unitsInOrder = unitsInOrder;
         this.discounted = discounted;
         this.condition = condition;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public boolean isdiscounted() {
        return discounted;
    }

    public void setdiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setProductImage(MultipartFile file) {
        this.productImage = file;
    }

    @XmlTransient
    public MultipartFile getProductImage() {
        return this.productImage;
    }

    public void setProductManual(MultipartFile file) {
        this.productManual = file;
    }

    @XmlTransient
    public MultipartFile getProductManual() {
        return this.productManual;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (unitsInStock != product.unitsInStock) return false;
        if (unitsInOrder != product.unitsInOrder) return false;
        if (discounted != product.discounted) return false;
        if (!Objects.equals(productId, product.productId)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(unitPrice, product.unitPrice)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(manufacturer, product.manufacturer))
            return false;
        if (!Objects.equals(category, product.category)) return false;
        return Objects.equals(condition, product.condition);
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (int) (unitsInStock ^ (unitsInStock >>> 32));
        result = 31 * result + (int) (unitsInOrder ^ (unitsInOrder >>> 32));
        result = 31 * result + (discounted ? 1 : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + "]";
    }


}
