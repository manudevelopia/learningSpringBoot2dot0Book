package info.developia.learnspringbook.one.controller;

import info.developia.learnspringbook.one.document.Chapter;
import info.developia.learnspringbook.one.repository.ChapterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterRepository chapterRepository;

    public ChapterController(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @GetMapping
    public Flux<Chapter> listing(){
        return chapterRepository.findAll();
    }
}
