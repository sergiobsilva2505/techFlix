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
    private LocalDateTime publicationDate;

    public VideoMapper(String title, String description, CategoryMapper category, LocalDateTime publicationDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryMapper getCategory() {
        return category;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public void setCategory(CategoryMapper category) {
        this.category = category;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
