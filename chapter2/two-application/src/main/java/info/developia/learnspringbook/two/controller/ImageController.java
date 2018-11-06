package info.developia.learnspringbook.two.controller;

import info.developia.learnspringbook.two.domain.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @GetMapping
    Flux<Image> get() {
        return Flux.just(
                Image.builder().id("1").filename("filename1.jpg").build(),
                Image.builder().id("2").filename("filename2.jpg").build(),
                Image.builder().id("3").filename("filename3.jpg").build()
        );
    }

    @PostMapping
    Mono<Void> create(@RequestBody Flux<Image> images) {
        return images
                .map(image -> {
                    log.info("Images: " + image + " will be stored soon");
                    return image;
                })
                .then();
    }
}
