package webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import webstore.exception.NoProductsFoundUnderCategoryException;
import webstore.exception.ProductNotFoundException;
import webstore.domain.Product;
import webstore.service.ProductService;
import webstore.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        Set<Product> products = productService.getProductsByCategory(productCategory);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
                                      Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/{category}/{ByCriteria}")
    public String getProductByCategoryAndPriceFilterAndManufacturer(@PathVariable("category")
                                                                    String category,
                                                                    @MatrixVariable(pathVar = "ByCriteria")
                                                                    Map<String, List<String>> filterParams,
                                                                    @RequestParam("manufacturer")
                                                                    String manufacturer,
                                                                    Model model) {
        model.addAttribute("products", productService.getProductsByCategoryAndPriceFilterAndManufacturer(category, filterParams, manufacturer));
        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result,
                                           HttpServletRequest request) {
        if(result.hasErrors()) {
            return "addProduct";
        }
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        MultipartFile productImage = newProduct.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String pathName = rootDirectory + "resources/images/" + newProduct.getProductId() + ".png";
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(pathName));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }

        MultipartFile productManual = newProduct.getProductManual();
        String pathNameForManuals = rootDirectory + "resources/pdf/" + newProduct.getProductId() + ".pdf";
        if (productManual != null && !productManual.isEmpty()) {
            try {
                productManual.transferTo(new File(pathNameForManuals));
            } catch (Exception e) {
                throw new RuntimeException("Product Manual saving failed", e);
            }
        }
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @RequestMapping("/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url",
                req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder",
                "discontinued");
        binder.setAllowedFields("productId",
                "name",
                "unitPrice",
                "description",
                "manufacturer",
                "category",
                "unitsInStock",
                "productImage",
                "productManual",
                "language");
        binder.setValidator(productValidator);
    }
}
