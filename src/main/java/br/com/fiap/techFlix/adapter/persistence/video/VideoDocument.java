package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "videos")
public class VideoDocument {

    @Id
    private String id;
    private String title;
    private String description;
    private List<CategoryDocument> categories;
    private VideoDetailsDocument details;
    private LocalDateTime publicationDate;

    public VideoDocument(String id, String title, String description, List<CategoryDocument> categories, VideoDetailsDocument details, LocalDateTime publicationDate) {
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<CategoryDocument> getCategories() {
        return categories;
    }

    public int getLikes() {
        return details.getLikes();
    }

    public int getViews() {
        return details.getViews();
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
}
