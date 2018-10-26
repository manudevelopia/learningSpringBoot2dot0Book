package info.developia.learspringbook.one.repository;

import info.developia.learspringbook.one.document.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
    
}
