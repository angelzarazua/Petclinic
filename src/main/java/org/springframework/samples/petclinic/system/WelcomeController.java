package org.springframework.samples.petclinic.system;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

    @GetMapping("/")
    public String welcomeRoot() {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("welcome");
        return "welcome";
    }
}
