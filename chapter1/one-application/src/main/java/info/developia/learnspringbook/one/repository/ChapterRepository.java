package info.developia.learnspringbook.one.repository;

import info.developia.learnspringbook.one.document.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
    
}
