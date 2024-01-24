package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "videos")
@CompoundIndex(name = "categories_views", def = "{'categories.name': 1, 'details.views': 1}")
public class VideoDocument {

    @Id
    private String id;
    private String title;
    private String description;
    private List<CategoryDocument> categories;
    private VideoDetails details;
    private LocalDateTime publicationDate;

    public VideoDocument(String id, String title, String description, List<CategoryDocument> categories, LocalDateTime publicationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.details = new VideoDetails(0);
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
}
