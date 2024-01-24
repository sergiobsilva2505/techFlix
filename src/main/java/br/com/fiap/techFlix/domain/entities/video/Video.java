package br.com.fiap.techFlix.domain.entities.video;

import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.validation.Validator;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public class Video {

    private String id;
    private String title;
    private String description;
    private List<Category> categories;
    private LocalDateTime publicationDate;

    public Video(String id, String title, String description, List<Category> categories, LocalDateTime publicationDate) {
        Validator.notEmptyOrNull(id, "video id");
        Validator.notEmptyOrNull(title, "video title");
        Validator.notEmptyOrNull(description, "video description");
        Validator.notEmptyOrNull(categories, "video categories");
        Validator.objectNotNull(publicationDate, "video publication date");
        this.id = id;
        this.title = title;
        this.description = description;
        this.categories = categories;
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

    public List<Category> getCategories() {
        return categories;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public URI getUrl() {
        return URI.create("/videos/play/" + this.id);
    }
}
