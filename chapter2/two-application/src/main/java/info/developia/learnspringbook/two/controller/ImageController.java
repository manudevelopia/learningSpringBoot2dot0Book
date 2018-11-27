package info.developia.learnspringbook.two.controller;

import info.developia.learnspringbook.two.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    private static final String FILENAME = "{filename:.+}";

    private ImageService imageService;

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
					return ResponseEntity.badRequest().	body("Could not found: " + filename + " => " + e.getMessage());
				}
			});
    }

    @PostMapping
    public Mono<String> createFile(@RequestPart(name = "file") Flux<FilePart> files) {
        return imageService.createNew(files).then(Mono.just("redirect:/"));
    }

    @DeleteMapping
    public Mono<String> deleteFile(@PathVariable String filename) {
        return imageService.delete(filename).then(Mono.just("redirect:/"));
    }

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

}
