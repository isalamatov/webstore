package webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import webstore.misc.Clock;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Model model, Clock clock) {
        clock.start();
        System.out.println(clock.getTimeLine());
        model.addAttribute("timeLine", clock.getTimeLine());
        return "welcome";
    }

    @RequestMapping("/welcome/greeting")
    public String greeting() {
        return "welcome";
    }
}
