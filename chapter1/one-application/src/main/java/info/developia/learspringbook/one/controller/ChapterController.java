package info.developia.learspringbook.one.controller;

import info.developia.learspringbook.one.document.Chapter;
import info.developia.learspringbook.one.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterController(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @GetMapping
    public Flux<Chapter> listing(){
        return chapterRepository.findAll();
    }
}
