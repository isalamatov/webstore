package webstore.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class ProductImageValidator implements ConstraintValidator<ProductImage, MultipartFile> {

    private long allowedSize;

    @Override
    public void initialize(ProductImage productImage) {
        this.allowedSize = productImage.size();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return file.getSize() <= allowedSize;
    }

    public void setAllowedSize(long allowedSize) {
        this.allowedSize = allowedSize;
    }
}
