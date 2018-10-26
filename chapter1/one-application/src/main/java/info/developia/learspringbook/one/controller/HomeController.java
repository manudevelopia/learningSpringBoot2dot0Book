package info.developia.learspringbook.one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String greetings(@RequestParam(required = false, defaultValue = "") String name){
        return "Hi" + (name.isEmpty() ? "" : ", " + name);
    }

}
