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
    private VideoDetails details;
    private LocalDateTime publicationDate;

    public Video(String id, String title, String description, List<Category> categories, VideoDetails details, LocalDateTime publicationDate) {
        Validator.notEmptyOrNull(id, "video id");
        Validator.notEmptyOrNull(title, "video title");
        Validator.notEmptyOrNull(description, "video description");
        Validator.notEmptyOrNull(categories, "video categories");
        Validator.notNull(details, "video details");
        Validator.notNull(publicationDate, "video publication date");
        this.id = id;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.details = details;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public URI getUrl() {
        return URI.create("/videos/play/" + this.id);
    }

    public int getLikes() {
        return details.getLikes();
    }

    public int getViews() {
        return details.getViews();
    }

    public VideoDetails getDetails() {
        return details;
    }
}
