package br.com.fiap.techFlix.domain.entities.video;

import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.validation.Validator;
import org.springframework.util.Assert;

import java.net.URI;
import java.time.LocalDateTime;

public class Video {

    private String id;
    private String title;
    private String description;
    private Category category;
    private LocalDateTime publicationDate;

    public Video(String id, String title, String description, Category category, LocalDateTime publicationDate) {
        Validator.notEmptyOrNull(id, "video id");
        Validator.notEmptyOrNull(title, "video title");
        Validator.notEmptyOrNull(description, "video description");
        Validator.objectNotNull(category, "video category");
        Validator.objectNotNull(publicationDate, "video publication date");
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public URI getUrl() {
        Assert.hasText(this.id, "Id cannot be empty");
        return URI.create("/videos/play/" + this.id);
    }
}
