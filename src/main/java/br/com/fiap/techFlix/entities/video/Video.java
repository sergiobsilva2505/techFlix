package br.com.fiap.techFlix.entities.video;

import br.com.fiap.techFlix.entities.category.Category;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class Video {

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
}
