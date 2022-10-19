package webstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("greeting", "Here is my one and all brand new store.");
        model.addAttribute("tagline", "You can buy anything you want here.");
        return "welcome";
    }
}
