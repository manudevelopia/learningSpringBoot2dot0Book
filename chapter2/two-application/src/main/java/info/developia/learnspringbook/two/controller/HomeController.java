package info.developia.learnspringbook.two.controller;

import info.developia.learnspringbook.two.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class HomeController {


    private ImageService imageService;

    @GetMapping
    public Mono<String> index(Model model){
        model.addAttribute("images", imageService.getAllImages());

        return Mono.just("index");
    }

    public HomeController(ImageService imageService) {
        this.imageService = imageService;
    }
}
