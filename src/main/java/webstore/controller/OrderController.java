package webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import webstore.service.OrderService;
/**
Dummy for order controller. It is not used in actual implementation.
*/
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/P1234/2")
    public String process() {
        return "redirect:/products";
    }
}
