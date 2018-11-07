package info.developia.learnspringbook.two.controller;

import info.developia.learnspringbook.two.service.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
@RequestMapping("/images")
public class HomeController {

    private static final String FILENAME = "{filename:.+}";

    private ImageService imageService;

    @GetMapping
    public Mono<String> index(Model model){
        model.addAttribute("images", imageService.getAllImages());

        return Mono.just("index");
    }

    @GetMapping(value = "/" + FILENAME + "/raw", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Mono<ResponseEntity<?>> oneRawImage(@PathVariable String filename) {
        return imageService.findOneImage(filename)
                .map(resource -> {
                    try {
                        return ResponseEntity.ok()
                                .contentLength(resource.contentLength())
                                .body(new InputStreamResource(resource.getInputStream()));
                    } catch (IOException e) {
                        return ResponseEntity.badRequest().body("Could not found: " + filename + " => " + e.getMessage());
                    }
                });
    }

    @PostMapping
    public Mono<String> createFile(@RequestPart(name = "file") Flux<FilePart> files) {
        return imageService.createNew(files).then(Mono.just("redirect:/"));
    }

    @DeleteMapping
    public Mono<String> deleteFile(@PathVariable String filename){
        return imageService.delete(filename).then(Mono.just("redirect:/"));
    }

    public HomeController(ImageService imageService) {
        this.imageService = imageService;
    }
}
