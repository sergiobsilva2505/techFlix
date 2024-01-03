package br.com.fiap.techFlix.adapters.video;

import br.com.fiap.techFlix.adapters.category.CategoryMapper;
import br.com.fiap.techFlix.entities.category.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "videos")
public class VideoMapper {

    @Id
    private String id;
    private String title;
    private String description;
    private CategoryMapper category;
    private String url;
    private LocalDateTime publicationDate;
}
