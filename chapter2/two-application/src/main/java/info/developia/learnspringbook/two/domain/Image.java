package info.developia.learnspringbook.two.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private String id;
    private String filename;

    public Image(String id, String filename){
        this.id = id;
        this.filename = filename;
    }

}
