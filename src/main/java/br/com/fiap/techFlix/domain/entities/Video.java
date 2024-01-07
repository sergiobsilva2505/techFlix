package br.com.fiap.techFlix.domain.entities;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class Video {

    private String id;
    private String title;
    private String description;
    private Category category;
    private LocalDateTime publicationDate;

    public Video(String title, String description, Category category, LocalDateTime publicationDate) {
        Assert.hasText(title, "Title cannot be empty");
        Assert.hasText(description, "Description cannot be empty");
        Assert.notNull(category, "Category cannot be null");
        Assert.notNull(publicationDate, "Publication date cannot be null");
        this.title = title;
        this.description = description;
        this.category = category;
        this.publicationDate = publicationDate;
    }

    public Video(String id, String title, String description, Category category, LocalDateTime publicationDate) {
        this(title, description, category, publicationDate);

        Assert.hasText(id, "Id cannot be empty");
        this.id = id;
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
}
