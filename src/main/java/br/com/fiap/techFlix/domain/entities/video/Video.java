package br.com.fiap.techFlix.domain.entities.video;

import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.validation.Validator;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public URI getUrl() {
        return URI.create("/videos/play/" + this.id);
    }
}
